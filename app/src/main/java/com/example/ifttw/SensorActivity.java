package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mSensorProximity;
    private TextView mTextSensorProximity;

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if (mSensorProximity != null) {
//            mSensorManager.registerListener(this, mSensorProximity, SensorManager.SENSOR_DELAY_NORMAL);
//        }
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mSensorManager.unregisterListener(this);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        // Get an instance of the sensor manager from system service
        mSensorManager =
                (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mTextSensorProximity = (TextView) findViewById(R.id.label_proximity);
        mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        String sensor_error = getResources().getString(R.string.error_no_sensor);
        if (mSensorProximity == null) {
            mTextSensorProximity.setText(sensor_error);
        }
//        Intent i = new Intent(this, SensorService.class);
        startService();
        Button buttonStop =  findViewById(R.id.stop_sensor_serv);
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService();
            }
        });
    }
    // Stop the service
//    public void stopService(View view) {
//        stopService(new Intent(this, SensorService.class));
//    }
    public void startService() {
        Intent serviceIntent = new Intent(this, SensorService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");
        ContextCompat.startForegroundService(this, serviceIntent);
    }
    public void stopService() {
        Intent serviceIntent = new Intent(this, SensorService.class);
        stopService(serviceIntent);
    }
}
