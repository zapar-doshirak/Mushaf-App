package com.example.mushaf.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mushaf.data.model.Ayahs;
import com.example.mushaf.data.repository.SurahsRepository;

import java.util.List;

public class SurahViewModel extends AndroidViewModel {

    private SurahsRepository repository;
    private LiveData<List<Ayahs>> surahAyahs;
//    private LiveData<MushafResponse> surahAyahsTranslation;

    /**
     * We use the application as context in the constructor
     * because the ViewModel outlives the activities lifecycle.
     * To avoid memory leaks we use application as context instead of activities or views.
     */
    public SurahViewModel(@NonNull Application application) {
        super(application);

        repository = new SurahsRepository(application);
        //surahAyahs = repository.getSurahAyahs();

    }

    public LiveData<List<Ayahs>> getSurahAyahs(int surahNumber) {
        surahAyahs = repository.getSurahAyahs(surahNumber);
        return surahAyahs;
    }

//    public LiveData<MushafResponse> getSurahAyahsTranslation(){
//        surahAyahsTranslation = repository.getSurahAyahsTranslation();
//        return  surahAyahsTranslation;
//    }
}
