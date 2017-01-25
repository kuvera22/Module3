package com.example.kuvera.par;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }
    class DrawView extends View {

        Paint p;
        Path path;
        Point point;

        public DrawView(Context context) {
            super(context);
            p = new Paint();
            path = new Path();

            point=new Point(540,3000);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            p.setColor(Color.BLACK);
            p.setStrokeWidth(6);
            //canvas.drawLine(0,200,1000,200,p);

            p.setStyle(Paint.Style.FILL);
            canvas.drawPoint(point.x,point.y,p);

            path.reset();
            path.moveTo(0,200);
            path.quadTo(point.x,point.y,1080,200);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path,p);
        }
    }
}

