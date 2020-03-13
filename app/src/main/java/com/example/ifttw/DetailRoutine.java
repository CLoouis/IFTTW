package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import static com.example.ifttw.MyApp.db;

public class DetailRoutine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_routine);

        final TextView idRoutine = findViewById(R.id.label_id_routine_detail);
        idRoutine.setText(Integer.toString(getIntent().getIntExtra("idRoutine", 0)));

        TextView triggerType = findViewById(R.id.label_trigger_detail);
        int triggerCode = getIntent().getIntExtra("triggerType", 0);
        if (triggerCode == 1) {
            triggerType.setText("Timer : Daily");
        } else if (triggerCode == 2) {
            triggerType.setText("Timer : Weekly");
        } else if (triggerCode == 3) {
            triggerType.setText("Timer : One time");
        } else if (triggerCode == 4) {
            triggerType.setText("Sensor");
        }

        TextView actionType = findViewById(R.id.label_action_detail);
        int actionCode = getIntent().getIntExtra("actionType", 0);
        if (actionCode == 1) {
            actionType.setText("Notification");
        } else if (actionCode == 2) {
            actionType.setText("Turn On Wifi");
        } else if (actionCode == 3) {
            actionType.setText("Turn Off Wifi");
        } else if (actionCode == 4) {
            actionType.setText("Call External API");
        }

        TextView status = findViewById(R.id.label_status_detail);
        int statusCode = getIntent().getIntExtra("status", -1);
        if (statusCode == 0) {
            status.setText("Inactive");
        } else if (statusCode == 1) {
            status.setText("Active");
        }

        ToggleButton checkStatus = findViewById(R.id.statusRoutine);
        checkStatus.setChecked(statusCode == 1);

        Intent intent = new Intent(this, NotificationReceiver.class);
        final PendingIntent statusRoutinePendingIntent = PendingIntent.getBroadcast(
                this,
                getIntent().getIntExtra("idRoutine", 0),
                intent, PendingIntent.FLAG_NO_CREATE
        );
        checkStatus.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            // create new pending intent to create routine
                            // that have been cancelled
                            statusRoutinePendingIntent.cancel();

                            db.userDao().update(1, getIntent().getIntExtra("idRoutine", 0));
                        } else {
                            statusRoutinePendingIntent.cancel();
                            //set status in db to 0
                            db.userDao().update(0, getIntent().getIntExtra("idRoutine", 0));
                        }
                    }
                }
        );

        Button deleteRoutine = findViewById(R.id.deleteRoutine);
        // delete database and launch main
        deleteRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.userDao().deleteUsers(getIntent().getIntExtra("idRoutine", 0));
                goToHome(v);
            }
        });
    }

    public void goToHome(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}