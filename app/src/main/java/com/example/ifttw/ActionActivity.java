package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActionActivity extends AppCompatActivity {
    private Bundle bundleSource = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        Button notification = (Button) findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchNotificationActivity(view);
            }
        });

        Button turnOnWifi = findViewById(R.id.turnOnWifi);
        turnOnWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchTurnOnWifi(v);
            }
        });

        Button turnOffWifi = findViewById(R.id.turnOffWifi);
        turnOffWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchTurnOffWifi(v);
            }
        });

        Button callApi = findViewById(R.id.callApi);
        callApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCallApi(v);
            }
        });
        bundleSource = getIntent().getExtras();
    }

    public void launchNotificationActivity(View view) {
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.putExtras(bundleSource);
        startActivity(intent);
    }

    public void launchTurnOnWifi(View view) {
        Intent intent = new Intent(this, create_routine.class);
        intent.putExtras(bundleSource);
        intent.putExtra("actionType", 2);
        startActivity(intent);
    }

    public void launchTurnOffWifi(View view) {
        Intent intent = new Intent(this, create_routine.class);
        intent.putExtras(bundleSource);
        intent.putExtra("actionType", 3);
        startActivity(intent);
    }

    public void launchCallApi(View view) {
        Intent intent = new Intent(this, create_routine.class);
        intent.putExtras(bundleSource);
        intent.putExtra("actionType", 4);
        startActivity(intent);
    }
}
