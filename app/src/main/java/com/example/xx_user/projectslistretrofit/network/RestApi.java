package com.example.xx_user.projectslistretrofit.network;

import com.example.xx_user.projectslistretrofit.model.ProjectsWrapper;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface RestApi {

    @GET("3ssdemo/prj/json//projects.php")
    Call<ProjectsWrapper> getData();

 /*   @GET("3ssdemo/prj/json/galleryAssets.php")
    Call<> getAssetByProject(@Query("projectId") String projectId);*/
}
