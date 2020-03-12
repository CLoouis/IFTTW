package com.example.ifttw;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class TimerReceiver extends BroadcastReceiver implements ActionModule {
    private Calendar datePicked;
    private AlarmManager alarmManager;
    private int type;

    @Override
    public void onReceive(Context context, Intent intent) {
        long dateFromIntent = intent.getLongExtra("date", 0);
        datePicked = Calendar.getInstance();
        datePicked.setTimeInMillis(dateFromIntent);

        type = intent.getIntExtra("type", 3);

        alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        pushNotification(context, 0, "Title", "Test");
    }

    public void pushNotification(Context context, int id, String title, String detail) {
        Intent intent = new Intent(context, NotificationReceiver.class);
        intent.putExtra("idRoutine", id);
        intent.putExtra("title", title);
        intent.putExtra("description", detail);

        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (context, 3, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (type == 1) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    datePicked.getTimeInMillis(), AlarmManager.INTERVAL_DAY, notifyPendingIntent);
        }  else if (type == 2) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    datePicked.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, notifyPendingIntent);
        } else if (type == 3) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,
                    datePicked.getTimeInMillis(), notifyPendingIntent);
        }
    }
}
