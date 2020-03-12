package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CreateRoutine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);
        ImageView plus_bttn = findViewById(R.id.plusbttn);
        plus_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTriggerServiceActivity(view);
            }
        });

        TextView what = (TextView) findViewById(R.id.what);
        what.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActionActivity(v);
            }
        });

    }

    public void launchTriggerServiceActivity(View view) {
        Intent intent = new Intent(this, TriggerActivity.class);
        startActivity(intent);
    }

    public void launchActionActivity(View v) {
       Intent intent = new Intent(this, ActionActivity.class);
       startActivity(intent);
    }
}
