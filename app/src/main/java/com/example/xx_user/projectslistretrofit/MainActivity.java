package com.example.xx_user.projectslistretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.xx_user.projectslistretrofit.model.Project;
import com.example.xx_user.projectslistretrofit.model.ProjectsWrapper;
import com.example.xx_user.projectslistretrofit.network.RestApi;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://91.250.82.77:8081/";

    private Intent intentError;
    private Retrofit retrofit;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.recycler_view) RecyclerView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        recycleView.setLayoutManager(new LinearLayoutManager(this));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        retrofit = initRetrofit();
        loadData();
    }

    private void startErrorActivity() {
        intentError = new Intent(MainActivity.this, ErrorActivity.class);
        intentError.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intentError.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intentError.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intentError);
    }

    private void loadData() {
        RestApi service = retrofit.create(RestApi.class);

        Call<ProjectsWrapper> call = service.getData();

        call.enqueue(new Callback<ProjectsWrapper>() {
            @Override
            public void onResponse(Response<ProjectsWrapper> response, Retrofit retrofit) {
                if (response.isSuccess()) {

                    ProjectsWrapper projectsWrapper = response.body();
                    ListAdapter adapter = new ListAdapter(MainActivity.this, projectsWrapper.getProjectList(), new ICommand<Project>() {
                        @Override
                        public void execute(Project selectedProject) {
                            InfoActivity.launchActivity(MainActivity.this, selectedProject);
                        }
                    });
                    recycleView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                startErrorActivity();
            }
        });
    }

    @NonNull
    private Retrofit initRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
