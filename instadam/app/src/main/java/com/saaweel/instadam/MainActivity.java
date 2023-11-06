package com.saaweel.instadam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

/** @noinspection ALL*/
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.notifications) {
            Fragment fragment = new NotificationsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.primaryView, fragment).commit();
            return true;
        }

        if (id == R.id.chats) {
            Fragment fragment = new ChatsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.primaryView, fragment).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}