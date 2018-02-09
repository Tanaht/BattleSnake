package fr.istic.mmm.battlesnake.model;

import fr.istic.mmm.battlesnake.Constante;


public class Board {

    private Cell[][] cells;


    public Board() {
        this.cells = new Cell[Constante.NUMBER_OF_CELL_HEIGHT][Constante.NUMBER_OF_CELL_WIDTH];
        for (int i = 0; i < Constante.NUMBER_OF_CELL_HEIGHT; i++) {
            for (int j = 0; j < Constante.NUMBER_OF_CELL_WIDTH; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }



    public Cell getCell(int x, int y){
        return cells[x][y];
    }
}
