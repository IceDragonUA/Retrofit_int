package com.example.xx_user.projectslistretrofit.network;

import com.example.xx_user.projectslistretrofit.model.AssetsWrapper;
import com.example.xx_user.projectslistretrofit.model.ClientsWrapper;
import com.example.xx_user.projectslistretrofit.model.ProjectsWrapper;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface RestApi {

    @GET("3ssdemo/prj/json/projects.php")
    Call<ProjectsWrapper> getProjectData();

    @GET("3ssdemo/prj/json/clients.php")
    Call<ClientsWrapper> getClientData();

    @GET("3ssdemo/prj/json/galleryAssets.php")
    Call<AssetsWrapper> getAssetByProject(@Query("projectId") int projectId);
}


