package fr.istic.mmm.battlesnake.socket;


import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.Socket;

import fr.istic.mmm.battlesnake.Constante;
import fr.istic.mmm.battlesnake.model.Direction;

public class Client implements Runnable{
    private static final String TAG = "b.socket.Client";

    private Socket socketServer;

    public Client(String ipServer){
        try {
            socketServer = new Socket(ipServer, Constante.SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Impossible de ce connecté au serveur");
        }
    }

    public void sendDirectionToServeur(Direction direction){
        Gson gson = new Gson();
        byte[] bytes = gson.toJson(direction).getBytes();
        try {
            socketServer.getOutputStream().write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "Impossible d'envoyer la direction au serveur");
        }
    }


    public void goToLeft(){
        sendDirectionToServeur(Direction.LEFT);
    }

    public void goToRight(){
        sendDirectionToServeur(Direction.RIGHT);
    }

    public void goToUp(){
        sendDirectionToServeur(Direction.TOP);
    }

    public void goToDown(){
        sendDirectionToServeur(Direction.BOT);
    }


    @Override
    public void run() {

    }
}
