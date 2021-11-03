package com.example.mainscreen.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mainscreen.models.Sures;

@Database(entities = {Sures.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract MyDao getMyDao();


}
