package com.example.ifttw.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoutinesDAO {

    @Query("SELECT * FROM Routines")
    List<Routines> getAll();

    //Example Custum Query
    @Query("SELECT * FROM Routines WHERE id LIKE :id ")
    Routines findByName(int id);

    @Insert
    void insertAll(Routines... routine);

    @Delete
    public void deleteUsers(Routines... routine);

}
