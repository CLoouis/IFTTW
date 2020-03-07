package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class create_routine extends AppCompatActivity {

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
    }

    public void launchTriggerServiceActivity(View view) {
        Intent intent = new Intent(this, TriggerActivity.class);
        startActivity(intent);
    }
}
