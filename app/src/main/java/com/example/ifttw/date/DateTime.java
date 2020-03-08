package com.example.ifttw.date;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ifttw.R;

public class DateTime extends AppCompatActivity implements View.OnClickListener {

    Button everyDay;
    Button everyWeekAt;
    Button oneTimeAt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);

        everyDay = findViewById(R.id.button2);
        everyWeekAt = findViewById(R.id.button3);
        oneTimeAt = findViewById(R.id.button4);

        everyDay.setOnClickListener(this);
        everyWeekAt.setOnClickListener(this);
        oneTimeAt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == everyDay) {
            launchDate1(v);
        } else if (v == everyWeekAt) {
            launchDate2(v);
        } else if (v == oneTimeAt) {
            launchDate3(v);
        }
    }

    public void launchDate1(View view) {
        Intent intent = new Intent(this, TriggerDate1.class);
        startActivity(intent);
    }

    public void launchDate2(View view) {
        Intent intent = new Intent(this, TriggerDate2.class);
        startActivity(intent);
    }

    public void launchDate3(View view) {
        Intent intent = new Intent(this, TriggerDate3.class);
        startActivity(intent);
    }
}
