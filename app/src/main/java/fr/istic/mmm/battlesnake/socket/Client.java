package fr.istic.mmm.battlesnake.socket;


import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

import fr.istic.mmm.battlesnake.Constante;
import fr.istic.mmm.battlesnake.model.Cell;
import fr.istic.mmm.battlesnake.model.Direction;
import fr.istic.mmm.battlesnake.socket.Message.fromClient.MessageFromClient;
import fr.istic.mmm.battlesnake.socket.Message.fromServer.MessageFromServer;
import fr.istic.mmm.battlesnake.socket.Message.fromServer.MessageSendBoard;
import fr.istic.mmm.battlesnake.socket.Message.fromServer.MessageSendIdPlayer;
import fr.istic.mmm.battlesnake.view.CustomViewBoard;

import static fr.istic.mmm.battlesnake.socket.Message.fromClient.TypeMessageClientToServer.NEXT_DIRECTION_PLAYER;

public class Client implements Runnable{
    private static final String TAG = "b.socket.Client";

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(Integer.MAX_VALUE);

    private String ipServer;
    private CustomViewBoard boardView;
    private Socket socketServer;

    public Client(String ipServer, CustomViewBoard boardView){
        this.ipServer = ipServer;
        this.boardView = boardView;
    }


    public Future<Void> sendDirectionToServeur(Direction direction){
        Log.i(TAG,"client : direction send to server");
        return executorService.submit(()->{
            Gson gson = new Gson();
            byte[] bytes = gson.toJson(new MessageFromClient(NEXT_DIRECTION_PLAYER,direction)).getBytes();
            try {
                socketServer.getOutputStream().write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Impossible d'envoyer la direction au serveur");
            }
            return null;
        });
    }

    public Future<Void> goToLeft(){
        return sendDirectionToServeur(Direction.LEFT);
    }

    public Future<Void> goToRight(){
        return sendDirectionToServeur(Direction.RIGHT);
    }

    public Future<Void> goToUp(){
        return sendDirectionToServeur(Direction.TOP);
    }

    public Future<Void> goToDown(){
        return sendDirectionToServeur(Direction.BOT);
    }

    public void connectToServer(){
        try {
            socketServer = new Socket(ipServer, Constante.SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Impossible de ce connecté au serveur");
        }
    }

    @Override
    public void run() {
        connectToServer();
        while(!socketServer.isClosed()){

            byte[] bytes = new byte[100000];
            int numberOfBytesRead = 0;
            try {
                numberOfBytesRead = socketServer.getInputStream().read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "client : new data receive from server ");

            Gson gson = new Gson();

            String jsonObject = new String(bytes,0,numberOfBytesRead);

            MessageFromServer<?> msg = gson.fromJson(jsonObject, MessageFromServer.class);

            switch (msg.getMsgFromServer()){
                case BOARD_TO_DRAW:
                    MessageSendBoard boardMsg = gson.fromJson(jsonObject, MessageSendBoard.class);

                    Cell[][] cells = boardMsg.getData();
                    boardView.drawBoard(cells);
                    break;
                case PLAYER_ID:
                    MessageSendIdPlayer idPlayerMsg = gson.fromJson(jsonObject, MessageSendIdPlayer.class);
                    break;
                case PLAYER_WIN:
                    break;
                case PLAYER_LOSE:
                    break;

                default:
                    Log.e(TAG, "client : msg from server not implemented :"+msg.getMsgFromServer());
                    break;
            }
        }
    }
}
