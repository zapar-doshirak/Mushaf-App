package com.example.mushaf.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mushaf.data.model.Ayahs;
import com.example.mushaf.data.model.Sures;

@Database(entities = {Sures.class, Ayahs.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    private static final String DB_NAME = "mushaf.db";
    //create the instance so we wouldn't create multiple instances of the database
    // and use the same instance throughout the application which is accessed through the static variable
    private static MyDatabase instance;
    //access to dao
    public abstract MyDao myDao();

    /**
     * This method creates a single instance of the database,
     * then we can call this method from the outside so we have a handle to it.
     * Synchronized means that only one thread at a time can access the database
     * eliminating creating multiple instances.
     */
    public static synchronized MyDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MyDatabase.class, DB_NAME)
                    .createFromAsset("database/sures.db")
                    .build();
        }return instance;
    }
}