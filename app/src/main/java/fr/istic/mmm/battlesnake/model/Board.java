package fr.istic.mmm.battlesnake.model;

import fr.istic.mmm.battlesnake.Constante;

/**
 * Created by loic on 08/02/18.
 */

public class Board {

    private Cell[][] cells;


    public Board() {
        this.cells = new Cell[Constante.NUMBER_OF_CELL_HEIGHT][Constante.NUMBER_OF_CELL_WIDTH];

    }
}
