package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class create_routine extends AppCompatActivity {
    private Intent createRoutineIntent = new Intent(this, MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);

        Intent intent = getIntent();
        int triggerType = intent.getIntExtra("triggerType", 0);
        int actionType = intent.getIntExtra("actionType", 0);

        if (triggerType != 0) {
            createRoutineIntent.putExtra("triggerType", triggerType);
            if (triggerType == 1) {
                createRoutineIntent.putExtra("hour", intent.getIntExtra("hour", 0));
                createRoutineIntent.putExtra("minute", intent.getIntExtra("minute", 0));
            } else if (triggerType == 2) {
                createRoutineIntent.putExtra("day", intent.getIntExtra("day", 0));
                createRoutineIntent.putExtra("hour", intent.getIntExtra("hour", 0));
                createRoutineIntent.putExtra("minute", intent.getIntExtra("minute", 0));
            } else if (triggerType == 3) {
                createRoutineIntent.putExtra("year", intent.getIntExtra("year", 0));
                createRoutineIntent.putExtra("month", intent.getIntExtra("month", 0));
                createRoutineIntent.putExtra("day", intent.getIntExtra("day", 0));
                createRoutineIntent.putExtra("hour", intent.getIntExtra("hour", 0));
                createRoutineIntent.putExtra("minute", intent.getIntExtra("minute", 0));
            }
        }

        if (actionType != 0) {
            createRoutineIntent.putExtra("actionType", actionType);
            if (actionType == 1) {
                   createRoutineIntent.putExtra("title", "");
                   createRoutineIntent.putExtra("detail", "");
            }
        }

        ImageView plus_bttn = findViewById(R.id.plusbttn);
        plus_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchTriggerServiceActivity(view);
            }
        });

        TextView what = findViewById(R.id.what);
        if (actionType == 1) {
            what.setText("Notification");
        } else if (actionType == 2) {
            what.setText("Turn On Wifi");
        } else if (actionType == 3) {
            what.setText("Turn Off Wifi");
        }
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

    public void createRoutine(View v) {
        // create Routine and return to Home

    }
}

