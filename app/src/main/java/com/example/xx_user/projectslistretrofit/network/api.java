package com.example.xx_user.projectslistretrofit.network;

import com.example.xx_user.projectslistretrofit.model.Project;
import java.util.List;
import retrofit.Call;
import retrofit.http.GET;

public interface api {

    @GET("/projects.php")
    Call<List<Project>> getData();
}
