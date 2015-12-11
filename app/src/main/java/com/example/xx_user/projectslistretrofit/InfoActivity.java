package com.example.xx_user.projectslistretrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.xx_user.projectslistretrofit.model.Project;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InfoActivity extends AppCompatActivity {

    public static String LOG_TAG = "myLogs";


    private static final String SELECTED_PROJECT_KEY = "selected_project";

    @Bind(R.id.scrollView) LockableScrollView scrolling;

    private Project selectedProject;

    public static void launchActivity(Context activityContext, Project selectedProject) {

        Intent launchingIntent = new Intent(activityContext, InfoActivity.class);
        launchingIntent.putExtra(SELECTED_PROJECT_KEY, selectedProject);
        activityContext.startActivity(launchingIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        ButterKnife.bind(this);

        parseArgument();

        //int position = getFromMainActivity();


    }

    private void parseArgument() {
        selectedProject = getIntent().getParcelableExtra(SELECTED_PROJECT_KEY);
        Log.d(LOG_TAG, " " + selectedProject.getProjectName());
    }

    public int getFromMainActivity() {
        Intent intent = getIntent();

        //Log.d(LOG_TAG,"project" + intent.getExtras().getSerializable("project"));

        return intent.getExtras().getParcelable("project");
    }

}
