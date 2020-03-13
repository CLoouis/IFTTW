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

    @Query("SELECT * FROM Routines WHERE status = 1")
    public List<Routines> getAllActive();

    @Query("SELECT * FROM Routines WHERE status = 0")
    public List<Routines> getAllInactive();

    //Example Custom Query
    @Query("SELECT * FROM Routines WHERE idRoutine LIKE :id ")
    public Routines getById(int id);

    @Query("UPDATE Routines SET status=:newStatus WHERE idRoutine = :id")
    public void update(int newStatus, int id);

    @Query("INSERT INTO Routines VALUES (:idRoutine, :triggerType, :year, :month, :day, :hour, :minute, :actionType, :title, :description, :status)")
    public void insert(int idRoutine, int triggerType, int year, int month, int day, int hour, int minute, int actionType, String title, String description, int status);

    @Query("DELETE FROM Routines WHERE idRoutine = :idRoutine")
    public void deleteUsers(int idRoutine);

}
