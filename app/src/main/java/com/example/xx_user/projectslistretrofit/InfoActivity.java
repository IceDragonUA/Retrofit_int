package com.example.xx_user.projectslistretrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.xx_user.projectslistretrofit.dao.DataDao;
import com.example.xx_user.projectslistretrofit.model.Client;
import com.example.xx_user.projectslistretrofit.model.Project;
import com.example.xx_user.projectslistretrofit.network.IDataLoadingResult;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InfoActivity extends AppCompatActivity implements View.OnTouchListener {

    private static final String SELECTED_PROJECT_KEY = "selected_project";

    @Bind(R.id.scrollView) LockableScrollView scrolling;
    @Bind(R.id.viewFlipper) ViewFlipper viewFlipper;
    @Bind(R.id.left) Button leftButton;
    @Bind(R.id.right) Button rightButton;
    @Bind(R.id.back) Button backButton;

    @Bind(R.id.logo) ImageView projectLogoView;
    @Bind(R.id.logoProgressBar) ProgressBar projectLogoProgressBar;

    @Bind(R.id.title) TextView projectNameView;
    @Bind(R.id.description) TextView projectDescriptionView;
    @Bind(R.id.technologies) TextView projectTechnologiesView;
    @Bind(R.id.supportedScreens) TextView projectSupportedScreensView;
    @Bind(R.id.solutionTypes) TextView projectSolutionTypesView;
    @Bind(R.id.clientName) TextView clientNameView;

    private float fromPosition;
    private Project selectedProject;
    private RelativeLayout.LayoutParams relativeLayoutParam, imageViewLayoutParam;

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



        actionOnViewFlipper();

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

    private void updateUiForClient(Client client){
        clientNameView.setText(client.getClientName());
    }

    public void updateUiForAssets(String[] projectAssets){
        final RelativeLayout relativeLayout[] = new RelativeLayout[projectAssets.length];
        ImageView imageView[] = new ImageView[projectAssets.length];
        initViewFlipperContentParams();
        for (int i = 0; i < projectAssets.length; i++) {
            relativeLayout[i] = new RelativeLayout(this);
            imageView[i] = new ImageView(this);
            relativeLayout[i].setLayoutParams(relativeLayoutParam);
            relativeLayout[i].addView(imageView[i], imageViewLayoutParam);
            Picasso.with(this).load(projectAssets[i]).into(imageView[i]);
            viewFlipper.addView(relativeLayout[i]);
        }
    }

    private void initViewFlipperContentParams() {
        relativeLayoutParam = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        imageViewLayoutParam = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        imageViewLayoutParam.addRule(RelativeLayout.CENTER_IN_PARENT);
    }

    private void actionOnViewFlipper() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.left:
                        viewFlipperMotionLeft();
                        break;

                    case R.id.right:
                        viewFlipperMotionRight();
                        break;

                    case R.id.back:
                        finish();
                        break;

                    default:
                        break;
                }
            }
        };
        leftButton.setOnClickListener(onClickListener);
        rightButton.setOnClickListener(onClickListener);
        backButton.setOnClickListener(onClickListener);
        viewFlipper.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        viewFlipper.requestFocus();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                fromPosition = event.getX();
                scrolling.setScrollingEnabled(false);
                break;
            case MotionEvent.ACTION_UP:
                float toPosition = event.getX();
                if (fromPosition > toPosition) {
                    viewFlipperMotionRight();
                } else if (fromPosition < toPosition) {
                    viewFlipperMotionLeft();
                }
                scrolling.setScrollingEnabled(true);
                break;
            default:
                break;
        }
        return true;
    }

    private void viewFlipperMotionLeft() {
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.go_prev_in));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.go_prev_out));
        viewFlipper.showPrevious();
    }

    private void viewFlipperMotionRight() {
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.go_next_in));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.go_next_out));
        viewFlipper.showNext();
    }
}
