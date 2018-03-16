package fr.istic.mmm.battlesnake.socket;


import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import fr.istic.mmm.battlesnake.Constante;
import fr.istic.mmm.battlesnake.model.Cell;
import fr.istic.mmm.battlesnake.model.Direction;
import fr.istic.mmm.battlesnake.model.Game;
import fr.istic.mmm.battlesnake.model.RoutineMessageContent;
import fr.istic.mmm.battlesnake.model.cellContent.Apple;
import fr.istic.mmm.battlesnake.model.cellContent.CellContent;

import static fr.istic.mmm.battlesnake.Constante.TIME_EACH_FRAME_IN_MILISECONDE;

public class Server implements Runnable {
    private static final String TAG = "b.socket.Server";

    private ServerSocket serverSocket;
    private int nbOfPlayer;

    private Game game;
    private List<ServerPlayerRepresentation> serverPlayerRepresentations;
    private ScheduledExecutorService periodicScheduler;


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

        for (ServerPlayerRepresentation client : serverPlayerRepresentations){
            new Thread(client).start();
        }

        Log.i(TAG, "server : début de la partie ...");
        game.startGame();
        game.generateApple();

        //periodicScheduler = Executors.newScheduledThreadPool(1);
        //periodicScheduler.scheduleAtFixedRate(this::gameRoutine, 0, TIME_EACH_FRAME_IN_MILISECONDE, TimeUnit.MILLISECONDS);

        for (ServerPlayerRepresentation client : serverPlayerRepresentations) {
            client.sendPlayerIdToClient();
            client.sendNumberOfPlayerInGame(nbOfPlayer);
        }


        //TODO attendre que tout les joueurs sois prets
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true){
            gameRoutine();
            try {
                Thread.sleep(TIME_EACH_FRAME_IN_MILISECONDE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void gameRoutine() {
        List<Direction> listPlaterDirection = new ArrayList<>();

        boolean appleEaten = false;
        for (ServerPlayerRepresentation client : serverPlayerRepresentations) {
            Direction directionPlayer = client.getNextDirection();
            CellContent content = game.moveSnakePlayer(directionPlayer, client.getIdPlayer());
            listPlaterDirection.add(directionPlayer);

            if (content instanceof Apple){
                appleEaten = true;
            }
        }

        if (appleEaten){
            game.generateApple();
        }

        Cell applePosition = game.getApplePosition();


        RoutineMessageContent routineMsg = new RoutineMessageContent(
                listPlaterDirection,
                applePosition.getCoordX(),
                applePosition.getCoordY()
        );

        for (ServerPlayerRepresentation client : serverPlayerRepresentations) {
            client.sendGameRoutine(routineMsg);
        }

        Log.i(TAG, "gameRoutine: ...");

    }

}
