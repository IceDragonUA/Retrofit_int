package com.example.xx_user.projectslistretrofit.network;


public interface IDataLoadingResult<T> {

    void onResult(T result);

    void onFailure(Throwable ex);

}
