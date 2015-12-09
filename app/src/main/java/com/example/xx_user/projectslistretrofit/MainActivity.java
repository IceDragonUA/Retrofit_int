package com.example.xx_user.projectslistretrofit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

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
    public static String LOG_TAG = "myLogs";

    private Retrofit retrofit;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.list) ListView listView;
    @Bind(R.id.fab) FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        retrofit = initRetrofit();
        loadData();
    }

    private void loadData() {
        RestApi service = retrofit.create(RestApi.class);

        Call<ProjectsWrapper> call = service.getData();

        call.enqueue(new Callback<ProjectsWrapper>() {
            @Override
            public void onResponse(Response<ProjectsWrapper> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    ProjectsWrapper projectsWrapper = response.body();
                    ListAdapter adapt = new ListAdapter(
                            getApplicationContext(),
                            R.layout.item,
                            projectsWrapper.getProjectList());
                    listView.setAdapter(adapt);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(LOG_TAG,"1233", t);
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
