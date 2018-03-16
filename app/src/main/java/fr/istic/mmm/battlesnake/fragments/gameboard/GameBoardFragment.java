package fr.istic.mmm.battlesnake.fragments.gameboard;

import fr.istic.mmm.battlesnake.model.Cell;


public interface GameBoardFragment {

    public void handlerMainThreadForDrawView(Cell[][] cells);
}
