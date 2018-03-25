package fr.istic.mmm.battlesnake.model;

import fr.istic.mmm.battlesnake.model.cellContent.Snake;


public class Player {

    private int playerId;
    private Snake snake;
    private boolean lose;
    private boolean win;


    public Player(int playerId) {
        this.playerId = playerId;
        lose = false;
        win = false;
    }

    public void removePlayerFromBoard(){
        snake.removeSnakeFromBoard();
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

    public boolean isLose() {
        return lose;
    }

    public void setLose(boolean lose) {
        this.lose = lose;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}
