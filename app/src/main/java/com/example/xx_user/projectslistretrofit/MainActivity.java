package com.example.xx_user.projectslistretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.xx_user.projectslistretrofit.model.Project;
import com.example.xx_user.projectslistretrofit.model.ProjectsWrapper;
import com.example.xx_user.projectslistretrofit.network.RestApi;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://91.250.82.77:8081/";

    private Intent intentInfo;
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
        startInfoActivity();
    }

    private void startInfoActivity() {
        intentInfo = new Intent(MainActivity.this, InfoActivity.class);

        /*recycleView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int i, long l) {
                intentInfo.putExtra("position", i);
                startActivity(intentInfo);
            }
        });*/
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
                            intentInfo = new Intent(MainActivity.this, InfoActivity.class);
                            startActivity(intentInfo);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
