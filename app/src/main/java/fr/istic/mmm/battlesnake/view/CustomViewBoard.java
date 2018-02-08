package fr.istic.mmm.battlesnake.view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import java.util.Random;

/**
 * Created by loic on 08/02/18.
 */

public class CustomViewBoard extends View {
    public CustomViewBoard(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Random rand = new Random();
        canvas.drawRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    public void drawBoard() {
        invalidate(); // redraws the view calling onDraw()
    }
}
