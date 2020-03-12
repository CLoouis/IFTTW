package com.example.ifttw;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.ifttw.Routines;
import com.example.ifttw.RoutinesDAO;

@Database(entities = {Routines.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RoutinesDAO userDao();
}