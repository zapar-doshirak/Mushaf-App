package com.example.mainscreen.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

public class Sures {

    private int number;
    private String name;
    private String nameTranslate;
    private String place;
    private int ayahsCount;

    public Sures() {
    }

    public Sures(int number, String name, String nameTranslate, String place, int ayahsCount) {
        this.number = number;
        this.name = name;
        this.nameTranslate = nameTranslate;
        this.place = place;
        this.ayahsCount = ayahsCount;
    }

    //без перевода (для списка сур)
    public Sures(int number, String name, String place, int ayahsCount) {
        this.number = number;
        this.name = name;
        this.place = place;
        this.ayahsCount = ayahsCount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameTranslate() {
        return nameTranslate;
    }

    public void setNameTranslate(String nameTranslate) {
        this.nameTranslate = nameTranslate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getAyahsCount() {
        return ayahsCount;
    }

    public void setAyahsCount(int ayahsCount) {
        this.ayahsCount = ayahsCount;
    }
}
