package fr.istic.mmm.battlesnake;

import android.graphics.Color;

import fr.istic.mmm.battlesnake.model.Cell;
import fr.istic.mmm.battlesnake.model.Direction;

import static fr.istic.mmm.battlesnake.model.Direction.*;

public class Constante {
    public static final int NUMBER_OF_CELL_HEIGHT = 40;
    public static final int NUMBER_OF_CELL_WIDTH = 30;

    public static final Cell[][] LIST_POSITION_INITIAL_SNAKE = {
            {new Cell(NUMBER_OF_CELL_WIDTH/2,5),new Cell(NUMBER_OF_CELL_WIDTH/2,4),new Cell(NUMBER_OF_CELL_WIDTH/2,3)},
            {new Cell(NUMBER_OF_CELL_WIDTH/2,NUMBER_OF_CELL_HEIGHT-5),new Cell(NUMBER_OF_CELL_WIDTH/2,NUMBER_OF_CELL_HEIGHT-4),new Cell(NUMBER_OF_CELL_WIDTH/2,NUMBER_OF_CELL_HEIGHT-3)}
    };

    public static final Direction[] LIST_DIRECTION_INITIAL_SNAKE = {
           BOT, TOP
    };

    public static final int LIST_COLOR_SNAKE[] = {
            Color.BLUE,Color.RED
    };

    public static final int COLOR_WALL = Color.BLACK;
    public static final int COLOR_EMPTY_CELL = Color.WHITE;
    public static final int COLOR_APPLE = Color.GREEN;


    public static final int SERVER_PORT = 6000;

    public static final int TIME_EACH_FRAME_IN_MILISECONDE = 1000;


}
