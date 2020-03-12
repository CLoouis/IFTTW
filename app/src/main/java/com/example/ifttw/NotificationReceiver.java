package com.example.ifttw;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.ifttw.date.TriggerDate3;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class NotificationReceiver extends BroadcastReceiver {
//    private NotificationManager mNotificationManager;
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        // On receive parsing the intent to know which action to do
        // if action type = 1 -> push notification
        // action type = 2 -> turn on wifi
        // action type = 3 -> turn off wifi
        int actionType = intent.getIntExtra("actionType", 0);
        if (actionType == 1) {
            int NOTIFICATION_ID =  intent.getIntExtra("idRoutine", 0);
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("description");

            deliverNotification(context, NOTIFICATION_ID, title, description);
        } else if (actionType == 2) {
            turnOnWifi(context);
        } else if (actionType == 3){
            turnOffWifi(context);
        } else {
            deliverNotification(context, 0, "Aneh", "ada yang salah");
        }

    }

    private void deliverNotification(Context context, int NOTIFICATION_ID, String title, String description) {
        // Create a notification with NOTIFICATION_ID (id routine) and given title + description
        NotificationManager mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent contentIntent = new Intent(context, MainActivity.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_logo)
                .setContentTitle(title)
                .setContentText(description)
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private void turnOnWifi(Context context){
        // Turn on the wifi
        // For android Q, there is restriction to access setWifiEnabled
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (wifiManager != null) {
                wifiManager.setWifiEnabled(true);
            }
        }
    }

    private void turnOffWifi(Context context) {
        // Turn off the wifi
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (wifiManager != null) {
                wifiManager.setWifiEnabled(false);
            }
        }
    }
}
