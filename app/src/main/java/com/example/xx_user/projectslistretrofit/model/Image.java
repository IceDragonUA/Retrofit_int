package com.example.xx_user.projectslistretrofit.model;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Image {

    @SerializedName("url")
    private String projectImageUrl;

    public Image() {
    }

    public String getProjectImageUrl() {
        return projectImageUrl;
    }
}
