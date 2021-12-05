package com.example.mushaf.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mushaf.data.model.Ayahs;
import com.example.mushaf.data.model.Sures;
import com.example.mushaf.data.repository.SurahsRepository;

import java.util.List;

public class SurahViewModel extends AndroidViewModel {

    private SurahsRepository repository;
    private LiveData<List<Ayahs>> allAyahs;

    /**
     * We use the application as context in the constructor
     * because the ViewModel outlives the activities lifecycle.
     * To avoid memory leaks we use application as context instead of activities or views.
     */
    public SurahViewModel(@NonNull Application application) {
        super(application);

        repository = new SurahsRepository(application);
        allAyahs = repository.getAllAyahs();

    }

    public LiveData<List<Ayahs>> getAllAyahs() {
        return allAyahs;
    }
}
