package com.jun.bluetoothpainter;

/**
 * Created by jun on 2016. 5. 11..
 */
public class DrawPoint {
    private float x;
    private float y;
    private boolean isDraw;

    public DrawPoint(float x, float y, boolean draw){
        this.x = x;
        this.y = y;
        this.isDraw = draw;
    }

    public boolean getDraw(){
        return isDraw;
    }
    public void setDraw(boolean draw){
        isDraw = draw;
    }
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

}
