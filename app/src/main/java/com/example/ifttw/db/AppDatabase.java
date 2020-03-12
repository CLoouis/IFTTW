package com.example.ifttw.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Routines.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RoutinesDAO userDao();
}