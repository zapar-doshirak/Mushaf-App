package com.example.mushaf.data.net.response;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;

public class Translation {

    @Json(name = "number")
    private int ayahNumber;

    @Json(name = "text")
    private String ayahTranslation;

    public Translation(int ayahNumber, String ayahTranslation) {
        this.ayahNumber = ayahNumber;
        this.ayahTranslation = ayahTranslation;
    }

    public int getAyahNumber() {
        return ayahNumber;
    }

    public void setAyahNumber(int ayahNumber) {
        this.ayahNumber = ayahNumber;
    }

    public String getAyahTranslation() {
        return ayahTranslation;
    }

    public void setAyahTranslation(String ayahTranslation) {
        this.ayahTranslation = ayahTranslation;
    }

    @Override
    public String toString() {
        return "Translation{" +
                "ayahNumber=" + ayahNumber +
                ", ayahTranslation='" + ayahTranslation + '\'' +
                '}';
    }
}
