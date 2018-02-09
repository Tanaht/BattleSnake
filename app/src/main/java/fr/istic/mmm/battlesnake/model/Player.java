package fr.istic.mmm.battlesnake.model;

import fr.istic.mmm.battlesnake.model.cellContent.Snake;


public class Player {

    private int playerId;
    private Snake snake;


    public Player(int playerId) {
        this.playerId = playerId;
    }


    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public int getPlayerId() {
        return playerId;
    }
}
