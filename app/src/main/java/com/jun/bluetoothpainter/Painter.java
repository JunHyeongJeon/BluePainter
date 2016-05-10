package com.jun.bluetoothpainter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by jun on 2016. 5. 11..
 */
public class Painter extends View {
    private Paint mPaint;
    private Path mPath;

    private static final float MINP = 0.25f;
    private static final float MAXP = 0.75f;

    private Bitmap mBitmap;
    Bitmap bm;
    private Canvas mCanvas;
    // private Path mPath;
    private Paint mBitmapPaint;



    public Painter(Context context){
        super(context);
        DisplayMetrics metrics = new DisplayMetrics();

        ((WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(metrics);

        mBitmap = Bitmap.createBitmap(metrics.widthPixels,
                metrics.heightPixels, Bitmap.Config.ARGB_8888);

        mCanvas = new Canvas(mBitmap);
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        mCanvas.drawColor(0xFFFFFFFF); // backgroundcolor
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);

    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    /**
     */
    private void touch_start(float x, float y) {
        // mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    /**
     */
    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    /**
     */
    private void touch_up() {
        mPath.lineTo(mX, mY);
        mCanvas.drawPath(mPath, mPaint);
        // mPath.reset();
    }

    String stingbuffer;
    float stringtemp;
    float xtemp;
    float ytemp;
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x=stringtemp = event.getX();
        float y = event.getY();
        DrawPoint p = new DrawPoint(x, y, false);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // sendMessage("GO");
                stingbuffer="SX";
                stingbuffer+=(char)(stringtemp/100+'0');
                stringtemp%=100;
                stingbuffer+=(char)(stringtemp/10+'0');
                stringtemp%=10;
                stingbuffer+=(char)(stringtemp+'0');

                stringtemp=y;
                stingbuffer+="Y";
                stingbuffer+=(char)(stringtemp/100+'0');
                stringtemp%=100;
                stingbuffer+=(char)(stringtemp/10+'0');
                stringtemp%=10;
                stingbuffer+=(char)(stringtemp+'0');
                touch_start(x, y);
                xtemp=x;
                ytemp=y;

                break;
            case MotionEvent.ACTION_MOVE:
                if(900<(x-xtemp)*(x-xtemp)+(y-ytemp)*(y-ytemp))
                {
                    stringtemp=Math.abs(x-xtemp);
                    stringtemp=Math.abs(y-ytemp);
                    stringtemp=(float)Math.atan2(y-ytemp, x-xtemp);
                    if(stringtemp>0)
                    {
                        stringtemp=(stringtemp/(float)Math.PI)*180;
                    }
                    else
                    {
                        stringtemp=-stringtemp;
                        stringtemp=360-(stringtemp/(float)Math.PI)*180;
                    }
                    stingbuffer+="A";
                    stingbuffer+=(char)(stringtemp/100+'0');
                    stringtemp%=100;
                    stingbuffer+=(char)(stringtemp/10+'0');
                    stringtemp%=10;
                    stingbuffer+=(char)(stringtemp+'0');

                    stringtemp=(float)Math.sqrt((x-xtemp)*(x-xtemp)+(y-ytemp)*(y-ytemp));
                    stingbuffer+="D";
                    stingbuffer+=(char)(stringtemp/100+'0');
                    stringtemp%=100;
                    stingbuffer+=(char)(stringtemp/10+'0');
                    stringtemp%=10;
                    stingbuffer+=(char)(stringtemp+'0');

                    xtemp=x;
                    ytemp=y;
                }
                touch_move(x, y);

                break;
            case MotionEvent.ACTION_UP:
                stingbuffer+="x";
                stingbuffer+=(char)(stringtemp/100+'0');
                stringtemp%=100;
                stingbuffer+=(char)(stringtemp/10+'0');
                stringtemp%=10;
                stingbuffer+=(char)(stringtemp+'0');

                stringtemp=y;
                stingbuffer+="y";
                stingbuffer+=(char)(stringtemp/100+'0');
                stringtemp%=100;
                stingbuffer+=(char)(stringtemp/10+'0');
                stringtemp%=10;
                stingbuffer+=(char)(stringtemp+'0');
                stingbuffer+="s";

//                sendMessage(stingbuffer);
                break;
        }
        invalidate();
        return true;
    }

}



