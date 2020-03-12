package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ifttw.date.DateTime;

public class TriggerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trigger);
        Button date = findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSelectDateActivity(v);
            }
        });

        Button sensor = findViewById(R.id.sensor);
        sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSensorActivity(v);
            }
        });
    }

    public void launchSelectDateActivity(View v) {
        Intent intent = new Intent(this, DateTime.class);
        startActivity(intent);
    }

    public void launchSensorActivity(View v) {
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);
    }

}
