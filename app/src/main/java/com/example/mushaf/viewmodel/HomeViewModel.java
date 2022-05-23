package com.example.mushaf.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mushaf.data.model.Surah;
import com.example.mushaf.data.repository.SurahsRepository;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private final SurahsRepository repository;
    private final LiveData<List<Surah>> allSures;

    /**
     * We use the application as context in the constructor
     * because the ViewModel outlives the activities lifecycle.
     * To avoid memory leaks we use application as context instead of activities or views.
     */
    public HomeViewModel(@NonNull Application application) {
        super(application);
        repository = new SurahsRepository(application);
        allSures = repository.getAllSurahs();
    }

    public LiveData<List<Surah>> getAllSures() {
        return allSures;
    }
}