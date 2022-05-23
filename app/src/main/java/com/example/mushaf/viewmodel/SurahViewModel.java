package com.example.mushaf.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mushaf.data.model.Ayahs;
import com.example.mushaf.data.net.response.ApiResponse;
import com.example.mushaf.data.net.response.SurahResponse;
import com.example.mushaf.data.net.response.Translation;
import com.example.mushaf.data.repository.SurahsRepository;

import java.util.List;

public class SurahViewModel extends AndroidViewModel {

    private SurahsRepository repository;
    private LiveData<List<Ayahs>> surahAyahs;
    private LiveData<ApiResponse> ayahsTranslation;

    /**
     * We use the application as context in the constructor
     * because the ViewModel outlives the activities lifecycle.
     * To avoid memory leaks we use application as context instead of activities or views.
     */
    public SurahViewModel(@NonNull Application application) {
        super(application);
        repository = new SurahsRepository(application);
    }

//    public LiveData<List<Ayahs>> getAyahs(int surahNumber, String edition){
//        surahAyahs = repository.getSurahAyahs(surahNumber);
//        ayahsTranslation = repository.getTranslation(surahNumber, edition);
//
//    }

    public LiveData<List<Ayahs>> getSurahAyahs(int surahNumber) {
        surahAyahs = repository.getSurahAyahs(surahNumber);
        return surahAyahs;
    }

    public LiveData<ApiResponse> getAyahsTranslation(int surahNumber, String edition){
        ayahsTranslation = repository.getTranslation(surahNumber, edition);
        Log.d("surahviewmodel", "ayah translation is: " + ayahsTranslation);
        return  ayahsTranslation;
    }
}