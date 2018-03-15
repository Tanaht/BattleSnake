package fr.istic.mmm.battlesnake.socket;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import fr.istic.mmm.battlesnake.model.Direction;
import fr.istic.mmm.battlesnake.model.Player;

public class ServerPlayerRepresentation {

    private Player snakePlayer;
    private ServerSocket serverSocket;
    private Socket socket;
    private Direction nextDirection;


    public ServerPlayerRepresentation(ServerSocket serverSocket, Player snakePlayer) {
        this.snakePlayer = snakePlayer;
        this.serverSocket = serverSocket;
        this.nextDirection = Direction.TOP;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void waitAndSetNextDirection(){
            byte[] bytes = new byte[4096];
        try {
            socket.getInputStream().read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        nextDirection = gson.fromJson(new String(bytes), Direction.class);
    }
}
