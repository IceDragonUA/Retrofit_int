package com.example.xx_user.projectslistretrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.xx_user.projectslistretrofit.model.Project;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InfoActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://91.250.82.77:8081/";

//    public static String LOG_TAG = InfoActivity.class.getCanonicalName();

    private static final String SELECTED_PROJECT_KEY = "selected_project";

    @Bind(R.id.scrollView) LockableScrollView scrolling;
    @Bind(R.id.logo) ImageView projectLogo;
    @Bind(R.id.logoProgressBar) ProgressBar projectLogoProgressBar;

    @Bind(R.id.title) TextView projectName;
    @Bind(R.id.description) TextView projectDescription;
    @Bind(R.id.technologies) TextView projectTechnologies;
    @Bind(R.id.supportedScreens) TextView projectSupportedScreens;
    @Bind(R.id.solutionTypes) TextView projectSolutionTypes;

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

        projectName.setText(selectedProject.getProjectName());
        projectDescription.setText(selectedProject.getProjectDescription());
        projectTechnologies.setText(Arrays.toString(selectedProject.getProjectTechnologies()));
        projectSupportedScreens.setText(Arrays.toString(selectedProject.getProjectSupportedScreens()));
        projectSolutionTypes.setText(Arrays.toString(selectedProject.getProjectSolutionTypes()));

        Picasso.with(this)
                .load(selectedProject.getProjectImage().getProjectImageUrl())
                .into(projectLogo, new Callback() {
                    @Override
                    public void onSuccess() {
                        if (projectLogoProgressBar.getVisibility() == View.VISIBLE) {
                            projectLogoProgressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError() {
                        projectLogoProgressBar.setVisibility(View.GONE);
                    }
                });

    }

    private void parseArgument() {
        selectedProject = getIntent().getExtras().getParcelable(SELECTED_PROJECT_KEY);
    }

}
