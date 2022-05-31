package com.example.aplikacja6;

//https://stackoverflow.com/questions/12763104/move-a-ball-with-accelerometer-android

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor acceleometer;

    private ImageView ball;

    int xa=0;
    int ya=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ball = (ImageView) findViewById(R.id.ball);
        ball.setImageResource(R.drawable.ball);
        ball.scrollTo(xa, ya);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acceleometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor s = sensorEvent.sensor;
        if (s.getType() == Sensor.TYPE_ACCELEROMETER) {

            Log.i("pozycja X:", String.valueOf(sensorEvent.values[0]));
            Log.i("pozycja Y:", String.valueOf(sensorEvent.values[1]));
            Log.i("pozycja Z:", String.valueOf(sensorEvent.values[2]));
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}