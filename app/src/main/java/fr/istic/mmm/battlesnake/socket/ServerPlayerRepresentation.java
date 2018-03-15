package fr.istic.mmm.battlesnake.socket;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fr.istic.mmm.battlesnake.model.Cell;
import fr.istic.mmm.battlesnake.model.Direction;
import fr.istic.mmm.battlesnake.model.Player;
import fr.istic.mmm.battlesnake.socket.Message.fromClient.MessageFromClient;
import fr.istic.mmm.battlesnake.socket.Message.fromClient.MessageSendDirection;
import fr.istic.mmm.battlesnake.socket.Message.fromServer.MessageFromServer;
import fr.istic.mmm.battlesnake.socket.Message.fromServer.MessageSendBoard;
import fr.istic.mmm.battlesnake.socket.Message.fromServer.MessageSendIdPlayer;

import static fr.istic.mmm.battlesnake.socket.Message.fromServer.TypeMessageServerToClient.PLAYER_ID;

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



    public void sendBoardToDraw(Cell[][] board) throws IOException {
        Gson gson = new Gson();
        clientSocket.getOutputStream().write(gson.toJson(new MessageSendBoard(board)).getBytes());
    }

    public void sendPlayerIdToClient() throws IOException {
        Gson gson = new Gson();
        MessageSendIdPlayer msg = new MessageSendIdPlayer(snakePlayer.getPlayerId());
        clientSocket.getOutputStream().write(gson.toJson(msg).getBytes());
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
        try {
            sendPlayerIdToClient();
        } catch (IOException e) {
            Log.e(TAG, "server : Erreur lors de la transmission de l'id du joueur" );
            e.printStackTrace();
        }
        while(clientSocket.isConnected()){

            byte[] bytes = new byte[4096];
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
