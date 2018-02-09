package fr.istic.mmm.battlesnake.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Random;

import fr.istic.mmm.battlesnake.Constante;
import fr.istic.mmm.battlesnake.model.Board;
import fr.istic.mmm.battlesnake.model.Cell;
import fr.istic.mmm.battlesnake.model.cellContent.EmptyCell;
import fr.istic.mmm.battlesnake.model.cellContent.Wall;

/**
 * Created by loic on 08/02/18.
 */

public class CustomViewBoard extends View {

    private Paint paint = new Paint();
    private Cell[][] boardToDraw;

    public CustomViewBoard(Context context) {
        super(context);
    }

    public CustomViewBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewBoard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomViewBoard(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStrokeWidth(1);


        int heightRectangleInPixel = getHeight()/Constante.NUMBER_OF_CELL_HEIGHT -20;
        int widthRectangleInPixel = getWidth()/Constante.NUMBER_OF_CELL_WIDTH -20;

        for (int i = 0; i < boardToDraw.length; i++) {
            for (int j = 0; j < boardToDraw[i].length; j++) {
                paint.setColor(boardToDraw[i][j].getCellContent().getColorToDraw());

                paint.setStyle(Paint.Style.FILL);
                canvas.drawRect(i*heightRectangleInPixel,
                        j*widthRectangleInPixel,
                        i*heightRectangleInPixel+ heightRectangleInPixel,
                        j*widthRectangleInPixel+ widthRectangleInPixel,
                        paint);

                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Constante.COLOR_WALL);
                canvas.drawRect(i*heightRectangleInPixel,
                        j*widthRectangleInPixel,
                        i*heightRectangleInPixel+ heightRectangleInPixel,
                        j*widthRectangleInPixel+ widthRectangleInPixel,
                        paint);

            }
        }

    }

    public void drawBoard(Cell[][] cells) {
        boardToDraw = cells;
        invalidate(); // redraws the view calling onDraw()
    }
}
