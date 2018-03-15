package fr.istic.mmm.battlesnake.socket;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import fr.istic.mmm.battlesnake.model.Cell;
import fr.istic.mmm.battlesnake.model.Direction;
import fr.istic.mmm.battlesnake.model.Player;

public class ServerPlayerRepresentation {
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

    public void waitAndSetNextDirection() throws IOException {
        byte[] bytes = new byte[4096];
        clientSocket.getInputStream().read(bytes);
        Log.i(TAG, "new data receive from client : "+clientSocket.getInetAddress());

        Gson gson = new Gson();
        nextDirection = gson.fromJson(new String(bytes), Direction.class);
    }

    public void sendBoardToDraw(Cell[][] board) throws IOException {
        Gson gson = new Gson();
        clientSocket.getOutputStream().write(gson.toJson(board).getBytes());
    }

    public void sendPlayerIdToClient() throws IOException {
        Gson gson = new Gson();
        clientSocket.getOutputStream().write(gson.toJson(snakePlayer).getBytes());
    }
}
