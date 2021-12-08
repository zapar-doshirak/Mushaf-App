package com.example.mushaf.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.mushaf.data.database.MyDao;
import com.example.mushaf.data.database.MyDatabase;
import com.example.mushaf.data.model.Ayahs;
import com.example.mushaf.data.model.Sures;

import java.util.List;

public class SurahsRepository {

    private MyDao myDao;
    private LiveData<List<Sures>> allSures;
    private LiveData<List<Ayahs>> surahAyahs;

    public SurahsRepository(Application application){

        MyDatabase myDatabase = MyDatabase.getInstance(application);

        myDao = myDatabase.myDao();

        allSures = myDao.getAllSures();
    }

    public LiveData<List<Sures>> getAllSures() {
        return allSures;
    }

    public LiveData<List<Ayahs>> getSurahAyahs(int surahNumber) {
        surahAyahs = myDao.getSurahAyahs(surahNumber);
        return surahAyahs;
    }
}
