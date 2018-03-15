package fr.istic.mmm.battlesnake.model;

import fr.istic.mmm.battlesnake.model.cellContent.CellContent;
import fr.istic.mmm.battlesnake.model.cellContent.EmptyCell;

public class Cell {

    private int coordX;
    private int coordY;
    private CellContent content;

    public Cell(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
        content = new EmptyCell();
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public CellContent getContent() {
        return content;
    }

    public void setContent(CellContent content) {
        this.content = content;
    }
}
