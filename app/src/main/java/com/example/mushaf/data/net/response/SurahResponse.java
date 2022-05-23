package com.example.mushaf.data.net.response;

import com.squareup.moshi.Json;

import java.util.List;

public class SurahResponse {
    @Json(name = "number")
    private int number;

    @Json(name = "ayahs")
    private List<Translation> translationList;

    public SurahResponse(int number, List<Translation> translationList) {
        this.number = number;
        this.translationList = translationList;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Translation> getTranslationList() {
        return translationList;
    }

    public void setTranslationList(List<Translation> translationList) {
        this.translationList = translationList;
    }

    @Override
    public String toString() {
        return "SurahResponse{" +
                "number=" + number +
                ", translationList=" + translationList +
                '}';
    }
}