package com.example.ifttw;

import android.content.Context;

public interface ActionModule {
    void pushNotification(Context context, int id, String title, String detail);
    void turnOnWifi(Context context, int id);
    void turnOffWifi(Context context, int id);
    void sendRequest(Context context, int id);
}
