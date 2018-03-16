package fr.istic.mmm.battlesnake.fragments.gameboard;


import android.app.Fragment;

import fr.istic.mmm.battlesnake.model.Cell;

public abstract class GameBoardFragment extends Fragment{

    public GameBoardFragment(){

    }

    public abstract void handlerMainThreadForDrawView(Cell[][] cells);
}
