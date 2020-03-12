package com.example.ifttw;

import android.app.Application;

import androidx.room.Room;

import com.example.ifttw.AppDatabase;

public class MyApp extends Application {

    public static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"Routines")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

}