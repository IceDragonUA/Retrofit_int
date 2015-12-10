package com.example.xx_user.projectslistretrofit;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ErrorActivity extends AppCompatActivity {

    private Boolean exit = false;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (exit) {
            finish();
        } else {
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error);
    }
}
