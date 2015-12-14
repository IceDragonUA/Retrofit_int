package com.example.xx_user.projectslistretrofit.model;

import com.google.gson.annotations.SerializedName;

public class Client {

    @SerializedName("id")
    private int clientId;

    @SerializedName("name")
    private String clientName;

    public Client() {
    }

    public int getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }
}
