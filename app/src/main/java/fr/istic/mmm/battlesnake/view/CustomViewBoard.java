package fr.istic.mmm.battlesnake.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import fr.istic.mmm.battlesnake.Constante;
import fr.istic.mmm.battlesnake.model.Cell;

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
        if(boardToDraw != null){
            paint.setStrokeWidth(1);
            int heightRectangleInPixel = getHeight()/Constante.NUMBER_OF_CELL_HEIGHT;
            int widthRectangleInPixel = getWidth()/Constante.NUMBER_OF_CELL_WIDTH;

            for (int i = 0; i < boardToDraw.length; i++) {
                for (int j = 0; j < boardToDraw[i].length; j++) {
                    paint.setColor(boardToDraw[i][j].getContent().getColorToDraw());

                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawRect(i*widthRectangleInPixel,
                            j*heightRectangleInPixel,
                            i*widthRectangleInPixel + widthRectangleInPixel,
                            j*heightRectangleInPixel + heightRectangleInPixel,
                            paint);

                    paint.setStyle(Paint.Style.STROKE);
                    paint.setColor(Constante.COLOR_WALL);
                    canvas.drawRect(i*widthRectangleInPixel,
                            j*heightRectangleInPixel,
                            i*widthRectangleInPixel + widthRectangleInPixel,
                            j*heightRectangleInPixel + heightRectangleInPixel,
                            paint);

                }
            }

        }


    }

    public void drawBoard(Cell[][] cells) {
        boardToDraw = cells;
        invalidate(); // redraws the view calling onDraw()
    }
}
