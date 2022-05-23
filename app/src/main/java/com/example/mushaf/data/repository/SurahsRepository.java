package com.example.mushaf.data.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mushaf.data.database.MyDao;
import com.example.mushaf.data.database.MyDatabase;
import com.example.mushaf.data.model.Ayahs;
import com.example.mushaf.data.model.Sures;
import com.example.mushaf.data.net.APIInterface;
import com.example.mushaf.data.net.response.ApiResponse;
import com.example.mushaf.data.net.response.SurahResponse;
import com.example.mushaf.data.net.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurahsRepository {

    private static final String TAG = "SurahsRepository";
    private final MyDao myDao;
    private final LiveData<List<Sures>> allSures;
    private LiveData<List<Ayahs>> surahAyahs;
    private final APIInterface apiInterface;

    public SurahsRepository(Application application){
        MyDatabase myDatabase = MyDatabase.getInstance(application);
        myDao = myDatabase.myDao();
        allSures = myDao.getAllSures();

        apiInterface = RetrofitClient.getClient().create(APIInterface.class);
    }

    public LiveData<List<Sures>> getAllSures() {
        return allSures;
    }

    public LiveData<List<Ayahs>> getSurahAyahs(int surahNumber) {
        surahAyahs = myDao.getSurahAyahs(surahNumber);
        return surahAyahs;
    }

    public LiveData <ApiResponse> getTranslation(int surahNumber, String edition) {
        MutableLiveData<ApiResponse> data = new MutableLiveData<>();
        apiInterface.getTranslation(surahNumber, edition)
                .enqueue(new Callback<ApiResponse>() {

                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        Log.d("resp", "response is: " + response.body());
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }

}
