package com.example.xx_user.projectslistretrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClientsWrapper {

    @SerializedName("clients")
    private List<Client> clientList;

    public ClientsWrapper() {
    }

    public List<Client> getClientList() {
        return clientList;
    }
}
