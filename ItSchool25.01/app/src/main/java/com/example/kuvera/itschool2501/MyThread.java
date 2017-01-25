package com.example.kuvera.itschool2501;


import android.animation.ArgbEvaluator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class MyThread extends Thread {
    private final int REDRAW_TIME = 10;//время перерисовки
    private final int ANIMATION_TIME = 1500;//продолжительность анимации
    private Paint mPaint;
    private ArgbEvaluator mARGBEvaluator;
    private final SurfaceHolder mSurfaceHolder; //нужен для получения canvas
    private boolean mRunning;//запущен ли процесс
    private long mStartTime;//вреня начала анимации;
    private long mRedrawTime;//предыдущее время перерисовки
    public MyThread(SurfaceHolder holder){
        mSurfaceHolder = holder;
        mRunning = false;
        mARGBEvaluator = new ArgbEvaluator();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }
    public void setRunning(boolean sr){
        mRunning = sr;
        mRedrawTime = getTime();
    }

    private long getTime() {
        return System.nanoTime()/(1000*1000);
    }

    public void run(){
        Canvas canvas;
        mStartTime = getTime();

        while (mRunning){
            long curTime=getTime();
            long elapsedTime = curTime-mRedrawTime;
            if (elapsedTime<REDRAW_TIME){
                // проверка прошло ли 100 мс
            }else {
                canvas = null;
                try {

                    canvas = mSurfaceHolder.lockCanvas();
                    synchronized (mSurfaceHolder) {
                        draw(canvas);
                    }
                } catch (NullPointerException e){}
                finally {
                    if (canvas !=null)
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            mRedrawTime=curTime;
        }

    }
    private void draw(Canvas canvas){
        long curTime = getTime() - mStartTime;
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        canvas.drawColor(Color.BLACK);

        int centerX = width/2;
        int centerY = height/2;

        float maxSize = Math.min(width,height)/2;

        float fraction = (float) (curTime%ANIMATION_TIME)/ANIMATION_TIME;
        int color = (int) mARGBEvaluator.evaluate(fraction, Color.YELLOW, Color.BLACK);
        mPaint.setColor(color);
        canvas.drawCircle(centerX, centerY, maxSize*fraction, mPaint);

    }

}
