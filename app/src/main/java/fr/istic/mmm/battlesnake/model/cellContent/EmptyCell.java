package fr.istic.mmm.battlesnake.model.cellContent;

import fr.istic.mmm.battlesnake.Constante;

/**
 * Created by loic on 09/02/18.
 */

public class EmptyCell implements CellContent {
    @Override
    public int getColorToDraw() {
        return Constante.COLOR_EMPTY_CELL;
    }
}
