package com.saaweel.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.loginButton).setOnClickListener(e -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new Login()).commit();
            findViewById(R.id.fragmentContainer).setVisibility(View.VISIBLE);
        });

        findViewById(R.id.registerButton).setOnClickListener(e -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new Register()).commit();
            findViewById(R.id.fragmentContainer).setVisibility(View.VISIBLE);
        });
    }
}