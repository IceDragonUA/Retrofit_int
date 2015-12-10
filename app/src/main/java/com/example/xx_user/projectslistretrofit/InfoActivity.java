package com.example.xx_user.projectslistretrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InfoActivity extends AppCompatActivity {

    public static String LOG_TAG = "myLogs";

    @Bind(R.id.scrollView) LockableScrollView scrolling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        int position = getFromMainActivity();
        ButterKnife.bind(this);



    }

    public int getFromMainActivity() {
        Intent intent = getIntent();
        return intent.getIntExtra("position", 0);
    }

}
