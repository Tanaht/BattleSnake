package fr.istic.mmm.battlesnake.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.istic.mmm.battlesnake.Constante;
import fr.istic.mmm.battlesnake.model.cellContent.Apple;
import fr.istic.mmm.battlesnake.model.cellContent.CellContent;
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

        List<Cell> initPosition = new ArrayList<>();

        initPosition.addAll(Arrays.asList(Constante.LIST_POSITION_INITIAL_SNAKE[players.size()]));

        Snake snake = new Snake(Constante.LIST_COLOR_SNAKE[players.size()],
                    newPlayer,
                    Constante.LIST_DIRECTION_INITIAL_SNAKE[players.size()],
                    initPosition,
                    board
                );

        for (int i = 0; i <  Constante.LIST_POSITION_INITIAL_SNAKE[players.size()].length; i++) {
            Cell currentCell = Constante.LIST_POSITION_INITIAL_SNAKE[players.size()][i];
            currentCell.setCellContent(snake);

            board.getCells()[currentCell.getCoordX()][currentCell.getCoordY()] = currentCell;
        }


        newPlayer.setSnake(snake);
        players.add(newPlayer);
        return newPlayer;
    }

    public CellContent moveSnakePlayer(Direction direction, int idPlayer){
        CellContent cellEaten = players.get(idPlayer).getSnake().move(direction);


        if (cellEaten instanceof Apple){
            board.generateApple();
        }
        return cellEaten;
    }

    public void startGame(){
        gameStarted = true;
        board.generateApple();

    }
    public Cell[][] getBoardCells(){
        return board.getCells();
    }
}
