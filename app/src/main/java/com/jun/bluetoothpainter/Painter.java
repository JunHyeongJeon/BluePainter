package com.jun.bluetoothpainter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by jun on 2016. 5. 11..
 */
public class Painter extends View {

    private Paint mPaint;
    private Path mPath;
    private float mX, mY;
    public static final int TOUCH_TOLERANCE = 4;

    public Painter(Context context){
        super(context);
        DisplayMetrics metrics = new DisplayMetrics();

        ((WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(metrics);

        mPaint = new Paint();
        mPath = new Path();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);

    }
    private void touchStart(float x, float y) {
        // mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touchUp() {
        mPath.lineTo(mX, mY);
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);

                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        Log.v("axis","x:"+x+" "+"y:"+y);
        invalidate();
        return true;
    }

    public Paint getmPaint() {
        return mPaint;
    }
}



