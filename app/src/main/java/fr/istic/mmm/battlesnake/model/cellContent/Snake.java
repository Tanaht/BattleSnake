package fr.istic.mmm.battlesnake.model.cellContent;

import java.util.List;

import fr.istic.mmm.battlesnake.model.Board;
import fr.istic.mmm.battlesnake.model.Cell;
import fr.istic.mmm.battlesnake.model.Direction;
import fr.istic.mmm.battlesnake.model.Player;


public class Snake extends CellContent {

    private transient Player player;
    private Direction directionAct;
    private transient List<Cell> cellList;
    private transient Board gameBoard;

    public Snake(int snakeColor, Player player, Direction directionAct, List<Cell> cellList, Board gameBoard){
        super(snakeColor);
        this.player = player;
        this.directionAct = directionAct;
        this.cellList = cellList;
        this.gameBoard = gameBoard;
    }

    public int getSize(){
        return cellList.size();
    }


    public Player getPlayer() {
        return player;
    }

    public CellContent move(Direction nextDirection){
        Direction realDirection = Direction.getRealDirection(directionAct, nextDirection);
        Cell snakeHead = cellList.get(0);
        CellContent cellContentToReturn;
        Cell nextHeadCell;
        switch (realDirection){
            case TOP:
                nextHeadCell = gameBoard.getCell(snakeHead.getCoordX(),snakeHead.getCoordY()-1);
                break;
            case BOT:
                nextHeadCell = gameBoard.getCell(snakeHead.getCoordX(),snakeHead.getCoordY()+1);
                break;
            case LEFT:
                nextHeadCell = gameBoard.getCell(snakeHead.getCoordX()-1,snakeHead.getCoordY());
                break;
            case RIGHT:
                nextHeadCell = gameBoard.getCell(snakeHead.getCoordX()+1,snakeHead.getCoordY());
                break;
            default:
                throw new RuntimeException("actual direction of snake is not implemented : "+realDirection);
        }
        directionAct = realDirection;
        cellContentToReturn = nextHeadCell.getContent();
        cellList.add(0,nextHeadCell);
        nextHeadCell.setContent(this);


        if (!(cellContentToReturn instanceof Apple)){
            cellList.get(cellList.size()-1).setContent(new EmptyCell());
            cellList.remove(cellList.size()-1);
        }

        return cellContentToReturn;
    }

    public void removeSnakeFromBoard() {
        for (Cell cell : cellList){
            cell.setContent(new EmptyCell());
        }
    }
}
