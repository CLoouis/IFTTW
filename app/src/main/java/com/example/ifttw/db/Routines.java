package com.example.ifttw.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Routines {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "condition")
    String condition;
    @ColumnInfo(name = "action")
    String action;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
}
