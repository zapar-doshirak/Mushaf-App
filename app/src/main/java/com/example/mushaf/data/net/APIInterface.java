package com.example.mushaf.data.net;

import com.example.mushaf.data.net.response.ApiResponse;
import com.example.mushaf.data.net.response.SurahResponse;
import com.example.mushaf.data.net.response.Translation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("surah/{surahNumber}/{edition}")
    Call<ApiResponse> getTranslation(
            @Path("surahNumber") int surahNumber,
            @Path("edition") String edition
    );

}
