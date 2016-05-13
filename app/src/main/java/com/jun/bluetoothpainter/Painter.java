package com.jun.bluetoothpainter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by jun on 2016. 5. 11..
 */
public class Painter extends View {

    private Paint mPaint;
    private Path mPath;
    private float mX, mY;
    public static final int TOUCH_TOLERANCE = 4;

    public Path getmPath() {
        return mPath;
    }

    public Painter(Context context){
        super(context);
        mPaint = new Paint();
        mPath = new Path();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);

    }
    public void touchStart(float x, float y) {
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }
    public void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }
    public void touchUp() {
        mPath.lineTo(mX, mY);
    }
    public Paint getmPaint() {
        return mPaint;
    }

}



