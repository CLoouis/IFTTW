package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailRoutine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_routine);

        TextView idRoutine = findViewById(R.id.label_id_routine_detail);
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
    }
}
