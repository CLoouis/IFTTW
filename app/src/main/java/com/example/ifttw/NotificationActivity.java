package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NotificationActivity extends AppCompatActivity {
    private Bundle bundleAction = new Bundle();
    EditText titleNotification;
    EditText detailNotification;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        bundleAction = getIntent().getExtras();

        titleNotification = findViewById(R.id.title_notif);
        detailNotification = findViewById(R.id.detail_notif);
        submit = findViewById(R.id.submit);

        bundleAction.putInt("actionType", 1);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreateRoutine(view);
            }
        });

    }

    public void goToCreateRoutine(View view) {
        Intent intent = new Intent(this, create_routine.class);
        bundleAction.putString("title", titleNotification.getText().toString());
        bundleAction.putString("description", detailNotification.getText().toString());
        intent.putExtras(bundleAction);
        startActivity(intent);
    }
}
