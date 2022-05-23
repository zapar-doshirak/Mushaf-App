package com.example.mushaf.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.squareup.moshi.Json;

@Entity(tableName = "sures_meta")
public class Sures implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "_id")
    @Json(name = "number")private int number;

    @ColumnInfo(name = "name_arabic")
    @Json(name = "name")private String arabicName;

    @ColumnInfo(name = "name")
    @Json(name = "englishName")private String name;

    @ColumnInfo(name = "name_translation")
    @Json(name = "englishNameTranslation")private String nameTranslate;

    @ColumnInfo(name = "place")
    @Json(name = "revelationType")private String place;

    @ColumnInfo(name = "ayahs_count")
    @Json(name = "numberOfAyahs")private int ayahsCount;

    @Ignore
    public Sures() {
    }

    public Sures(int number, String arabicName, String name, String nameTranslate, String place, int ayahsCount) {
        this.number = number;
        this.arabicName = arabicName;
        this.name = name;
        this.nameTranslate = nameTranslate;
        this.place = place;
        this.ayahsCount = ayahsCount;
    }

    //parcelable implementation
    protected Sures(Parcel in) {
        number = in.readInt();
        arabicName = in.readString();
        name = in.readString();
        nameTranslate = in.readString();
        place = in.readString();
        ayahsCount = in.readInt();
    }

    // parcelable implementation
    public static final Creator<Sures> CREATOR = new Creator<Sures>() {
        @Override
        public Sures createFromParcel(Parcel in) {
            return new Sures(in);
        }

        @Override
        public Sures[] newArray(int size) {
            return new Sures[size];
        }
    };

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getArabicName() {
        return arabicName;
    }

    public void setArabicName(String arabicName) {
        this.arabicName = arabicName;
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

    @Override
    public String toString() {
        return "Sures{" +
                "number=" + number +
                ", arabicName='" + arabicName + '\'' +
                ", name='" + name + '\'' +
                ", nameTranslate='" + nameTranslate + '\'' +
                ", place='" + place + '\'' +
                ", ayahsCount=" + ayahsCount +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(number);
        parcel.writeString(arabicName);
        parcel.writeString(name);
        parcel.writeString(nameTranslate);
        parcel.writeString(place);
        parcel.writeInt(ayahsCount);
    }
}