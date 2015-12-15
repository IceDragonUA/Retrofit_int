package com.example.xx_user.projectslistretrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xx_user.projectslistretrofit.dao.DataDao;
import com.example.xx_user.projectslistretrofit.model.Asset;
import com.example.xx_user.projectslistretrofit.model.AssetType;
import com.example.xx_user.projectslistretrofit.model.Client;
import com.example.xx_user.projectslistretrofit.model.Project;
import com.example.xx_user.projectslistretrofit.network.IDataLoadingResult;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InfoActivity extends AppCompatActivity {

    private static final String SELECTED_PROJECT_KEY = "selected_project";

    @Bind(R.id.pager) ViewPager viewPager;
    @Bind(R.id.back) Button backButton;

    @Bind(R.id.logo) ImageView projectLogoView;
    @Bind(R.id.logoProgressBar) ProgressBar projectLogoProgressBar;

    @Bind(R.id.title) TextView projectNameView;
    @Bind(R.id.description) TextView projectDescriptionView;
    @Bind(R.id.technologies) TextView projectTechnologiesView;
    @Bind(R.id.supportedScreens) TextView projectSupportedScreensView;
    @Bind(R.id.solutionTypes) TextView projectSolutionTypesView;
    @Bind(R.id.clientName) TextView clientNameView;

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
        updateUiForInfo();
        loadClient();
        loadAssets();
        actionOnViewPager();
    }

    private void parseArgument() {
        selectedProject = getIntent().getExtras().getParcelable(SELECTED_PROJECT_KEY);
    }

    private void updateUiForInfo() {
        projectNameView.setText(selectedProject.getProjectName());
        projectDescriptionView.setText(selectedProject.getProjectDescription());
        projectTechnologiesView.setText(Arrays.toString(selectedProject.getProjectTechnologies()));
        projectSupportedScreensView.setText(Arrays.toString(selectedProject.getProjectSupportedScreens()));
        projectSolutionTypesView.setText(Arrays.toString(selectedProject.getProjectSolutionTypes()));

        Picasso.with(this)
                .load(selectedProject.getProjectImage().getProjectImageUrl())
                .into(projectLogoView, new Callback() {
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

    private void loadClient() {

        DataDao.getInstance().getClientById(selectedProject.getClientId(), new IDataLoadingResult<Client>() {
            @Override
            public void onResult(Client result) {
                updateUiForClient(result);
            }

            @Override
            public void onFailure(Throwable ex) {

            }
        });

    }

    private void updateUiForClient(Client client) {
        clientNameView.setText(client.getClientName());
    }

    private void loadAssets() {

        DataDao.getInstance().getAssetsByProjectId(selectedProject.getProjectId(), AssetType.IMAGE, new IDataLoadingResult<List<Asset>>() {
            @Override
            public void onResult(List<Asset> result) {
                viewPager.setAdapter(new CustomPagerAdapter(InfoActivity.this, result));
            }

            @Override
            public void onFailure(Throwable ex) {

            }
        });

    }

    private void actionOnViewPager() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.back:
                        finish();
                        break;

                    default:
                        break;
                }
            }
        };
        backButton.setOnClickListener(onClickListener);
    }
}
