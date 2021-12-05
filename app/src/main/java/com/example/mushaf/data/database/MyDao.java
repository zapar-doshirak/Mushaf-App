package com.example.mushaf.data.database;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import com.example.mushaf.data.model.Ayahs;
import com.example.mushaf.data.model.Sures;

@Dao
public interface MyDao {

    @Query("SELECT * FROM ayahs ORDER BY rowid DESC")
    LiveData<List<Ayahs>> getAllAyahs();

    @Query("SELECT * FROM sures_meta")
    LiveData<List<Sures>> getAllSures();

}
