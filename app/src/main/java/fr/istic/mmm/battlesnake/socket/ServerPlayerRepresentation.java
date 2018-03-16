package fr.istic.mmm.battlesnake.socket;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fr.istic.mmm.battlesnake.model.Direction;
import fr.istic.mmm.battlesnake.model.Player;
import fr.istic.mmm.battlesnake.model.RoutineMessageContent;
import fr.istic.mmm.battlesnake.socket.Message.fromClient.MessageFromClient;
import fr.istic.mmm.battlesnake.socket.Message.fromClient.MessageSendDirection;
import fr.istic.mmm.battlesnake.socket.Message.fromServer.MessageSendGameRoutineMessage;
import fr.istic.mmm.battlesnake.socket.Message.fromServer.MessageSendIdPlayer;
import fr.istic.mmm.battlesnake.socket.Message.fromServer.MessageSendNumberOfPlayer;

public class ServerPlayerRepresentation implements Runnable {
    private static final String TAG = "b.s.ServerPlayer";

    private Player snakePlayer;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private Direction nextDirection;


    public ServerPlayerRepresentation(ServerSocket serverSocket, Player snakePlayer) {
        this.snakePlayer = snakePlayer;
        this.serverSocket = serverSocket;
        this.nextDirection = Direction.TOP;
    }

    public void setSocket(Socket socket) {
        this.clientSocket = socket;
    }

    public void sendGameRoutine(RoutineMessageContent routineData){
        Gson gson = new Gson();
        try {
            clientSocket.getOutputStream().write(gson.toJson(new MessageSendGameRoutineMessage(routineData)).getBytes());
        } catch (IOException e) {
            Log.e(TAG, "server : error when send direction to player" );
            e.printStackTrace();
        }
    }

    public void sendNumberOfPlayerInGame(int nb) {
        Gson gson = new Gson();
        try {
            clientSocket.getOutputStream().write(gson.toJson(new MessageSendNumberOfPlayer(nb)).getBytes());
        } catch (IOException e) {
            Log.e(TAG, "server : error when try to send number of player in game");
            e.printStackTrace();
        }
    }

    public void sendPlayerIdToClient(){
        Gson gson = new Gson();
        MessageSendIdPlayer msg = new MessageSendIdPlayer(snakePlayer.getPlayerId());
        try {
            clientSocket.getOutputStream().write(gson.toJson(msg).getBytes());
        } catch (IOException e) {
            Log.e(TAG, "server : Erreur lors de la transmission de l'id du joueur" );
            e.printStackTrace();
        }
    }

    public Direction getNextDirection() {
        return nextDirection;
    }

    public void  setNextDirection(Direction direction){
        nextDirection = direction;
    }

    public int getIdPlayer(){
        return snakePlayer.getPlayerId();
    }


    @Override
    public void run() {

        while(clientSocket.isConnected()){

            byte[] bytes = new byte[50000];
            int numberOfBytesRead = 0;
            try {
                numberOfBytesRead = clientSocket.getInputStream().read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "server : new data receive from client : "+clientSocket.getInetAddress());

            Gson gson = new Gson();

            String jsonObject = new String(bytes,0,numberOfBytesRead);

            MessageFromClient<?> msg = gson.fromJson(jsonObject, MessageFromClient.class);

            switch (msg.getMsgFromClient()){
                case NEXT_DIRECTION_PLAYER:
                    MessageSendDirection msgCorrectedParsed = gson.fromJson(jsonObject, MessageSendDirection.class);
                    setNextDirection(msgCorrectedParsed.getData());
                    Log.i(TAG, "server : new direction receive from client");
                    break;

                default:
                    Log.e(TAG, "server : msg from client not implemented :"+msg.getMsgFromClient());
                    break;
            }
        }
    }
}
