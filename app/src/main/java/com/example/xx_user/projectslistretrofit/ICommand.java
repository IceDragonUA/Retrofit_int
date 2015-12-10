package com.example.xx_user.projectslistretrofit;


public interface ICommand<T> {

    void execute(T param);

}
