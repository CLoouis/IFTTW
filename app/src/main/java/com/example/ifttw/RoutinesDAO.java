package com.example.ifttw;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.ifttw.Routines;

import java.util.List;

@Dao
public interface RoutinesDAO {

    @Query("SELECT * FROM Routines")
    public List<Routines> getAll();

    //Example Custom Query
    @Query("SELECT * FROM Routines WHERE idRoutine LIKE :id ")
    public Routines getById(long id);

    @Query("UPDATE Routines SET status=:newStatus WHERE idRoutine = :id")
    public void update(int newStatus, long id);

    @Query("INSERT INTO Routines VALUES (:idRoutine, :triggerType, :year, :month, :hour, :minute, :actionType, :title, :description, :status)")
    public void insert(long idRoutine, int triggerType, int year, int month, int hour, int minute, int actionType, String title, String description, int status);

    @Query("DELETE FROM Routines WHERE idRoutine = :idRoutine")
    public void deleteUsers(long idRoutine);

}
