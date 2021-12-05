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
    private LiveData<List<Ayahs>> allAyahs;

    public SurahsRepository(Application application){

        MyDatabase myDatabase = MyDatabase.getInstance(application);

        myDao = myDatabase.myDao();

        allSures = myDao.getAllSures();
        allAyahs = myDao.getAllAyahs();

    }

    public LiveData<List<Sures>> getAllSures() {
        Log.d("sures", "method getAllSures() from SurahsRepository");
        return allSures;
    }

    public LiveData<List<Ayahs>> getAllAyahs() {
        return allAyahs;
    }
}
