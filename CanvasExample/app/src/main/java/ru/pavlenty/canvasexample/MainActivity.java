package ru.pavlenty.canvasexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CanvasView customCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);


    }

    public void clearCanvas(View v) {
        customCanvas.clearCanvas();
    }
    public void DrawRed(View v){
        customCanvas.DrawRed();
    }
    public void DrawGreen(View v){
        customCanvas.DrawGreen();
    }
    public void DrawYello(View v){
        customCanvas.DrawYello();
    }
    public void DrawBlue(View v){
        customCanvas.DrawBlue();
    }

}
