package com.example.mushaf.data.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "ayahs", indices = {@Index("surahNumber")})
public class Ayahs {

    @PrimaryKey
    private int number;
    private int surahNumber;
    private String ayahText;
    private int numberInSurah;


    public Ayahs(int number, int surahNumber, String ayahText, int numberInSurah) {
        this.number = number;
        this.surahNumber = surahNumber;
        this.ayahText = ayahText;
        this.numberInSurah = numberInSurah;
    }

    @Ignore
    public Ayahs(String ayahText, int numberInSurah) {
        this.ayahText = ayahText;
        this.numberInSurah = numberInSurah;
    }

    public int getSurahNumber() {
        return surahNumber;
    }

    public void setSurahNumber(int surahNumber) {
        this.surahNumber = surahNumber;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAyahText() {
        return ayahText;
    }

    public void setAyahText(String ayahText) {
        this.ayahText = ayahText;
    }

    public int getNumberInSurah() {
        return numberInSurah;
    }

    public void setNumberInSurah(int numberInSurah) {
        this.numberInSurah = numberInSurah;
    }

}
