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

/**
 * Created by loic on 08/02/18.
 */

public class CustomViewBoard extends View {

    Paint paint = new Paint();

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
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawRect(30, 30, 80, 80, paint);


    }

    public void drawBoard() {
        invalidate(); // redraws the view calling onDraw()
    }
}
