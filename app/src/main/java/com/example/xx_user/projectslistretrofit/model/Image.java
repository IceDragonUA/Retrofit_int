package com.example.xx_user.projectslistretrofit.model;

import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("url")
    private String projectImageUrl;

    public Image() {
    }

    public String getProjectImageUrl() {
        return projectImageUrl;
    }
}
