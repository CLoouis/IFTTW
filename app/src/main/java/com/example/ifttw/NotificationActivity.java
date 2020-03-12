package com.example.ifttw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NotificationActivity extends AppCompatActivity {
    EditText titleNotification;
    EditText detailNotification;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        titleNotification = findViewById(R.id.title_notif);
        detailNotification = findViewById(R.id.detail_notif);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                pushNotification();
                goToHome(view);
            }
        });

    }

    public void goToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

//    public void pushNotification() {
//        Intent intent = new Intent(this, NotificationReceiver.class);
//        intent.putExtra("idRoutine", 0);
//        intent.putExtra("title", titleNotification.getText().toString());
//        intent.putExtra("description", detailNotification.getText().toString());
//        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
//                (this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        try {
//            notifyPendingIntent.send();
//        } catch (PendingIntent.CanceledException e) {
//            e.printStackTrace();
//        }
//    }
}
