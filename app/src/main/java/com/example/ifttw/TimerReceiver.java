package com.example.ifttw;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import java.io.PipedInputStream;
import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class TimerReceiver extends BroadcastReceiver implements ActionModule {
    private Calendar datePicked;
    private AlarmManager alarmManager;
    private int type;

    @Override
    public void onReceive(Context context, Intent intent) {
        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        int idRoutine = intent.getIntExtra("idRoutine", 0);
        long dateFromIntent = intent.getLongExtra("date", 0);
        datePicked = Calendar.getInstance();
        datePicked.setTimeInMillis(dateFromIntent);

        type = intent.getIntExtra("triggerType", 0);
        int actionType = intent.getIntExtra("actionType", 0);

        if (actionType == 1) {
            pushNotification(context, idRoutine, intent.getStringExtra("title"), intent.getStringExtra("description"));
        } else if (actionType == 2) {
            turnOnWifi(context, idRoutine);
        } else if (actionType == 3) {
            turnOffWifi(context, idRoutine);
        } else if (actionType == 4) {
            sendRequest(context, idRoutine);
        }
    }

    public void pushNotification(Context context, int id, String title, String detail) {
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.putExtra("idRoutine", id);
        intent.putExtra("actionType", 1);
        intent.putExtra("title", title);
        intent.putExtra("description", detail);

        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        doAction(notifyPendingIntent, type);
    }

    @Override
    public void turnOnWifi(Context context, int id) {
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.putExtra("idRoutine", id);
        intent.putExtra("actionType", 2);

        final PendingIntent turnOnPendingIntent = PendingIntent.getBroadcast(
                context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT
        );

        doAction(turnOnPendingIntent, type);
    }

    @Override
    public void turnOffWifi(Context context, int id) {
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.putExtra("idRoutine", id);
        intent.putExtra("actionType", 3);

        final PendingIntent turnOffPendingIntent = PendingIntent.getBroadcast(
                context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT
        );

       doAction(turnOffPendingIntent, type);
    }

    @Override
    public void sendRequest(Context context, int id) {
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.putExtra("idRoutine", id);
        intent.putExtra("actionType", 4);

        final PendingIntent sendRequestPendingIntent = PendingIntent.getBroadcast(
                context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT
        );

        doAction(sendRequestPendingIntent, type);
    }

    public void doAction(PendingIntent pendingIntent, int type) {
        Log.d("doAction", Long.toString(datePicked.getTimeInMillis()));

        if (type == 1) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    datePicked.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }  else if (type == 2) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    datePicked.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent);
        } else if (type == 3) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,
                    datePicked.getTimeInMillis(), pendingIntent);
        }
    }
}
