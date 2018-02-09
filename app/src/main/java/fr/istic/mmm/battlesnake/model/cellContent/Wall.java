package fr.istic.mmm.battlesnake.model.cellContent;

import fr.istic.mmm.battlesnake.Constante;


public class Wall implements CellContent{
    @Override
    public int getColorToDraw() {
        return Constante.COLOR_WALL;
    }
}
