package com.example.myapplication1801dima;

import android.animation.ArgbEvaluator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.Random;

public class MyThread extends Thread{
    private final int REDRAW_TIME = 1;
    private final long ANIMATION_TIME =10000000;
    private boolean flag;
    private long startTime;
    private long prevRedrawTime;
    private Paint paint;
    private ArgbEvaluator argbEvaluator;
    private SurfaceHolder surfaceHolder;

    @Override
    public void run(){
        Canvas c;
        startTime = getTime();
        while (flag){
            long currentTime = getTime();
            long elapsedTime = currentTime - prevRedrawTime;
            if (elapsedTime<REDRAW_TIME){
                continue;
            }
            c = null;
            c = surfaceHolder.lockCanvas();
            draw(c);
            surfaceHolder.unlockCanvasAndPost(c);
            prevRedrawTime = getTime();
        }



    }
    MyThread(SurfaceHolder h){
        flag = false;
        surfaceHolder = h ;
        paint= new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        argbEvaluator = new ArgbEvaluator();
    }

    public long getTime(){
        return System.nanoTime()/1000;
    }

    public void setRunning(boolean running){
        flag = running;
        prevRedrawTime = getTime();
    }

    public  void draw(Canvas c){
        Random r = new Random();

        int color = Color.rgb(r.nextInt(255),r.nextInt(255),r.nextInt(255));

        long curTime = getTime() - startTime;
        int w = c.getWidth();
        int h = c.getHeight();
        c.drawColor(Color.BLUE);
        int cx = w/2;
        int cy = h/2;
        float maxR = Math.min(w,h)/2;
        float fraction = (float)(curTime%ANIMATION_TIME)/ANIMATION_TIME;
        paint.setColor(color);
        color = Color.rgb(r.nextInt(255),r.nextInt(255),r.nextInt(255));

        c.drawColor(color);

        c.drawCircle(cx,cy,maxR*fraction,paint);

    }


}
