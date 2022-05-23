package com.example.mushaf.data.net.response;

import com.squareup.moshi.Json;

public class ApiResponse {
    @Json(name = "code")
    private int code;
    @Json(name = "status")
    private String status;
    @Json(name = "data")
    private SurahResponse surahResponse;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SurahResponse getSurahResponse() {
        return surahResponse;
    }

    public void setSurahResponse(SurahResponse surahResponse) {
        this.surahResponse = surahResponse;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", status='" + status + '\'' +
                ", surahResponse=" + surahResponse +
                '}';
    }
}