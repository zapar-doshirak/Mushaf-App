package com.example.mushaf.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import com.example.mushaf.data.model.Ayah;
import com.example.mushaf.data.model.Surah;

@Dao
public interface MyDao {
    @Query("SELECT * FROM ayahs WHERE surahNumber = :surahNumber")
    LiveData<List<Ayah>> getSurahAyahs(int surahNumber);

    @Query("SELECT * FROM sures_meta ")
    LiveData<List<Surah>> getAllSurahs();
}