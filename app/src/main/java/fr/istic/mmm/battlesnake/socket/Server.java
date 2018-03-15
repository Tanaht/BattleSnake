package fr.istic.mmm.battlesnake.socket;


import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import fr.istic.mmm.battlesnake.Constante;
import fr.istic.mmm.battlesnake.model.Game;

public class Server implements Runnable {
    private static final String TAG = "b.socket.Server";

    private ServerSocket serverSocket;
    private int nbOfPlayer;

    private Game game;
    private List<ServerPlayerRepresentation> serverPlayerRepresentations;

    public Server(int nbOfPlayer){
        this.nbOfPlayer = nbOfPlayer;
        game = new Game();
        serverPlayerRepresentations = new ArrayList<>();

        try {
            serverSocket = new ServerSocket(Constante.SERVER_PORT);
        } catch (IOException e) {
            Log.e(TAG, "Server: impossible de lancer le serveur, port occupé");
            throw new RuntimeException("impossible de lancer le serveur, port occupé");
        }

        for (int i = 0; i < nbOfPlayer; i++) {
            serverPlayerRepresentations.add(
                    new ServerPlayerRepresentation(serverSocket
                    ,game.addNewPlayer()));
        }

    }

    private void waitAllPlayerConnected() throws IOException {
        int numberPlayerConnected = 0;
        while(numberPlayerConnected < nbOfPlayer){
            Socket clientSocket = serverSocket.accept();

            serverPlayerRepresentations.get(numberPlayerConnected)
                    .setSocket(clientSocket);
            numberPlayerConnected++;

            Log.i(TAG, "nouveau joueur connecté : ip "+clientSocket.getInetAddress());
        }
        Log.i(TAG, " Tous les joueurs sont connectés");
    }

    @Override
    public void run() {
        Log.i(TAG, "serveur lancer adresse ip :"+serverSocket.getInetAddress()+", port :"+serverSocket.getLocalPort());
        try {
            Log.i(TAG, "Attente que les client ce connecte");
            waitAllPlayerConnected();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,"Une erreur est intervenue lors de l'attente de connexion des client au serveur");
            return;
        }

        Log.i(TAG, "début de la partie ...");

    }

}
