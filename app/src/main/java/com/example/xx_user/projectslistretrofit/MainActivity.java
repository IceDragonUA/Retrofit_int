package com.example.xx_user.projectslistretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.xx_user.projectslistretrofit.dao.DataDao;
import com.example.xx_user.projectslistretrofit.model.Project;
import com.example.xx_user.projectslistretrofit.network.IDataLoadingResult;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private Intent intentError;

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

        loadProjects();
    }

    private void startErrorActivity() {
        intentError = new Intent(MainActivity.this, ErrorActivity.class);
        intentError.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intentError.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intentError.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intentError);
    }

    private void loadProjects() {

        DataDao.getInstance().getProjects(new IDataLoadingResult<List<Project>>() {
            @Override
            public void onResult(List<Project> projects) {
                CustomListAdapter adapter = new CustomListAdapter(MainActivity.this, projects, new ICommand<Project>() {
                    @Override
                    public void execute(Project selectedProject) {
                        InfoActivity.launchActivity(MainActivity.this, selectedProject);
                    }
                });
                recycleView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Throwable ex) {
                startErrorActivity();
            }
        });
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
