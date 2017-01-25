package com.example.kuvera.itschool2501;

import android.content.Context;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    private MyThread mMyThread;
    public MySurfaceView(Context context){
        super(context);
        getHolder().addCallback(this);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mMyThread = new MyThread(getHolder());
        mMyThread.setRunning(true);
        mMyThread.start();// запуск в отдельном потоке
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //Изменяется  view (меняет размер)

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                mMyThread.join();
                retry=false;
            } catch (InterruptedException  e){
            //ловим исключение
                }
        }
    }
}
