package com.saaweel.healthcheckai.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.saaweel.healthcheckai.R;
import androidx.core.splashscreen.SplashScreen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashScreen.installSplashScreen(this);

        setContentView(R.layout.activity_main);
    }
}