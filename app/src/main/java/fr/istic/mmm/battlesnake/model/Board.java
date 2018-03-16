package fr.istic.mmm.battlesnake.model;

import android.os.Message;

import java.util.Random;

import fr.istic.mmm.battlesnake.Constante;
import fr.istic.mmm.battlesnake.model.cellContent.Apple;
import fr.istic.mmm.battlesnake.model.cellContent.EmptyCell;
import fr.istic.mmm.battlesnake.model.cellContent.Wall;


public class Board {

    private Cell[][] cells;
    private Cell applePosition;

    public Cell getApplePosition() {
        return applePosition;
    }

    public Board() {
        this.cells = new Cell[Constante.NUMBER_OF_CELL_WIDTH][Constante.NUMBER_OF_CELL_HEIGHT];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = new Cell(i, j);

                if (i == 0 || j == 0
                        || i == cells.length-1
                        || j == cells[i].length-1){
                    cell.setContent(new Wall());
                }

                cells[i][j] = cell;
            }
        }
    }

    public Cell generateApple(){
        Random rand = new Random();
        int randomX = 0;
        int randomY = 0;
        while (!(cells[randomX][randomY].getContent() instanceof EmptyCell)){
            randomX = rand.nextInt(Constante.NUMBER_OF_CELL_WIDTH);
            randomY = rand.nextInt(Constante.NUMBER_OF_CELL_WIDTH);
        }
        cells[randomX][randomY].setContent(new Apple());
        applePosition = cells[randomX][randomY];
        return cells[randomX][randomY];
    }

    public void setApple(int xPosition, int yPosition){
        if (applePosition != null
                && applePosition.getContent() instanceof Apple){
            applePosition.setContent(new EmptyCell());
        }
        cells[xPosition][yPosition].setContent(new Apple());
        applePosition = cells[xPosition][yPosition];
    }



    public Cell getCell(int x, int y){
        return cells[x][y];
    }

    public Cell[][] getCells() {
        return cells;
    }


}
