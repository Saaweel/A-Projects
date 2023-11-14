package com.saaweel.instadam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.startItem) {

            } else if (item.getItemId() == R.id.searchItem) {

            } else if (item.getItemId() == R.id.cameraItem) {

            } else if (item.getItemId() == R.id.notifyItem) {

            } else if (item.getItemId() == R.id.profileItem) {

            }

            return true;
        });
    }
}