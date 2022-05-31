package com.example.aplikacja6;

//https://stackoverflow.com/questions/12763104/move-a-ball-with-accelerometer-android
//https://stackoverflow.com/questions/6479637/android-accelerometer-moving-ball
//https://xiangchen.wordpress.com/2011/12/17/an-android-accelerometer-example/
//https://stackoverflow.com/questions/14882045/move-ball-around-screen-accelerometer-bounce-too

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    //private Bitmap mBitmap;

    private SensorManager sensorManager;
    private Sensor acceleometer;

    private ImageView ball;

    int xa= 0;
    int ya= 0;

    int x= 0;
    int y= 0;

    int xmax;
    int ymax;
    Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display  = getWindowManager().getDefaultDisplay();
        xmax = display.getWidth();
        ymax = display.getHeight();

        ball = (ImageView) findViewById(R.id.ball);
        ball.setImageResource(R.drawable.ball);
        //ball.setMaxHeight(50);
        //ball.setMaxWidth(50);
        ball.setX(xa);
        ball.setY(ya);
        //ball.scrollTo(xa, ya);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acceleometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        turnOn(SensorManager.SENSOR_DELAY_NORMAL);
    }


    void turnOn(int mode){
        if (acceleometer != null) {
            sensorManager.registerListener(this, acceleometer, mode);
        }
    }

    public void updateBallPosition(){
        ball.setX(x);
        ball.setX(y);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor s = sensorEvent.sensor;
        if (s.getType() == Sensor.TYPE_ACCELEROMETER) {

            Log.i("pozycja X:", String.valueOf(sensorEvent.values[0]));
            Log.i("pozycja Y:", String.valueOf(sensorEvent.values[1]));

            if(sensorEvent.values[0] > 0 && x < xmax){
                x++;
                updateBallPosition();
            }else if(sensorEvent.values[0] < 0 && x > 0){
                x--;
                updateBallPosition();
            }

            if(sensorEvent.values[1] > 0 && y < ymax){
                y++;
                updateBallPosition();
            }else if(sensorEvent.values[1] < 0 && y > 0){
                y--;
                updateBallPosition();
            }

        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        turnOn(SensorManager.SENSOR_DELAY_NORMAL);
    }

    /*
    public class CustomDrawableView extends View {
        public CustomDrawableView(Context context) {
            super(context);
            Bitmap ball = BitmapFactory.decodeResource(getResources(),
                    R.drawable.ball);
            final int dstWidth = 50;
            final int dstHeight = 50;
            mBitmap = Bitmap
                    .createScaledBitmap(ball, dstWidth, dstHeight, true);
            //mWood = BitmapFactory.decodeResource(getResources(), R.drawable.wood);

        }

        protected void onDraw(Canvas canvas) {
            final Bitmap bitmap = mBitmap;
            //canvas.drawBitmap(mWood, 0, 0, null);
            canvas.drawBitmap(bitmap, x, y, null);
            invalidate();
        }
    }
    */
    
}