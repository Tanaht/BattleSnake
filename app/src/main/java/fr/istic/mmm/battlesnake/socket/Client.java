package fr.istic.mmm.battlesnake.socket;


import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

import fr.istic.mmm.battlesnake.Constante;
import fr.istic.mmm.battlesnake.fragments.gameboard.GameboardFragmentSolo;
import fr.istic.mmm.battlesnake.model.Direction;
import fr.istic.mmm.battlesnake.model.Game;
import fr.istic.mmm.battlesnake.model.Player;
import fr.istic.mmm.battlesnake.socket.Message.fromClient.MessageFromClient;
import fr.istic.mmm.battlesnake.socket.Message.fromServer.MessageFromServer;
import fr.istic.mmm.battlesnake.socket.Message.fromServer.MessageSendGameRoutineMessage;
import fr.istic.mmm.battlesnake.socket.Message.fromServer.MessageSendIdPlayer;
import fr.istic.mmm.battlesnake.socket.Message.fromServer.MessageSendNumberOfPlayer;

import static fr.istic.mmm.battlesnake.socket.Message.fromClient.TypeMessageClientToServer.NEXT_DIRECTION_PLAYER;

public class Client implements Runnable{
    private static final String TAG = "b.socket.Client";

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(Integer.MAX_VALUE);

    private String ipServer;
    private GameboardFragmentSolo objForHandlerMainThread;
    private Socket socketServer;

    private Game game;
    private Player player;
    private int playerId;

    public Client(String ipServer, GameboardFragmentSolo objForHandlerMainThread){
        this.ipServer = ipServer;
        this.objForHandlerMainThread = objForHandlerMainThread;
        game = new Game();
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

            byte[] bytes = new byte[10000];
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
                case NUMBER_OF_PLAYER:
                    MessageSendNumberOfPlayer numberOfPlayerMsg = gson.fromJson(jsonObject, MessageSendNumberOfPlayer.class);

                    for (int i = 0;i<numberOfPlayerMsg.getData();i++){
                        if (i == playerId){
                            player = game.addNewPlayer();
                        }else{
                            game.addNewPlayer();
                        }
                    }

                    break;
                case PLAYER_ID:
                    MessageSendIdPlayer idPlayerMsg = gson.fromJson(jsonObject, MessageSendIdPlayer.class);
                    playerId = idPlayerMsg.getData();
                    break;

                case GAME_ROUTINE_MESSAGE:
                    MessageSendGameRoutineMessage routineMsg = gson.fromJson(jsonObject, MessageSendGameRoutineMessage.class);
                    List<Direction> directions = routineMsg.getData().getNewPlayerDirection();

                    for (int i = 0; i < directions.size() ; i++) {
                        game.moveSnakePlayer(directions.get(i),i);
                    }

                    int x = routineMsg.getData().getApplePositionX();
                    int y = routineMsg.getData().getApplePositionY();
                    game.setApple(x,y);

                    objForHandlerMainThread.handlerMainThreadForDrawView(game.getBoardCells());
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
