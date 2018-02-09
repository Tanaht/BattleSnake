package fr.istic.mmm.battlesnake.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.istic.mmm.battlesnake.Constante;
import fr.istic.mmm.battlesnake.model.cellContent.Snake;

public class Game {

    private Board board;
    private List<Player> players;

    private Boolean gameStarted;

    public Game() {
        players = new ArrayList<>();
        board = new Board();
        gameStarted = false;
    }

    public Player addNewPlayer(){
        if (gameStarted){
            throw new RuntimeException("Impossible d'ajouter un nouveau joueur alors que la partie à déjà commencer");
        }

        Player newPlayer = new Player(players.size());


        Snake snake = new Snake(Constante.LIST_COLOR_SNAKE[players.size()],
                    newPlayer,
                    Constante.LIST_DIRECTION_INITIAL_SNAKE[players.size()],
                    Arrays.asList(Constante.LIST_POSITION_INITIAL_SNAKE[players.size()]),
                    board
                );


        newPlayer.setSnake(snake);
        players.add(newPlayer);
        return newPlayer;
    }
}
