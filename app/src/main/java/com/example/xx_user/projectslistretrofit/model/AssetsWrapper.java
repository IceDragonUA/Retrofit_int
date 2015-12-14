package com.example.xx_user.projectslistretrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AssetsWrapper {

    @SerializedName("assets")
    private List<Asset> assetsList;

    public AssetsWrapper() {
    }

    public List<Asset> getAssetsList() {
        return assetsList;
    }
}
