package com.example.mainscreen.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import com.example.mainscreen.models.Sures;

@Dao
public interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addSurah(Sures surah);

    @Delete
    void deleteSurah(Sures surah);

    @Query("SELECT * FROM sures ORDER BY rowid DESC")
    List<Sures> getAllSures();

}
