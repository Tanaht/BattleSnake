package fr.istic.mmm.battlesnake.model;

import java.util.Random;

import fr.istic.mmm.battlesnake.Constante;
import fr.istic.mmm.battlesnake.model.cellContent.Apple;
import fr.istic.mmm.battlesnake.model.cellContent.EmptyCell;
import fr.istic.mmm.battlesnake.model.cellContent.Wall;


public class Board {

    private Cell[][] cells;


    public Board() {
        this.cells = new Cell[Constante.NUMBER_OF_CELL_WIDTH][Constante.NUMBER_OF_CELL_HEIGHT];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Cell cell = new Cell(i, j);

                if (i == 0 || j == 0
                        || i == cells.length-1
                        || j == cells[i].length-1){
                    cell.setCellContent(new Wall());
                }

                cells[i][j] = cell;
            }
        }
    }

    public void generateApple(){
        Random rand = new Random();
        int randomX = 0;
        int randomY = 0;
        while (!(cells[randomX][randomY].getCellContent() instanceof EmptyCell)){
            randomX = rand.nextInt(Constante.NUMBER_OF_CELL_WIDTH);
            randomY = rand.nextInt(Constante.NUMBER_OF_CELL_WIDTH);
        }
        cells[randomX][randomY].setCellContent(new Apple());
    }



    public Cell getCell(int x, int y){
        return cells[x][y];
    }

    public Cell[][] getCells() {
        return cells;
    }
}
