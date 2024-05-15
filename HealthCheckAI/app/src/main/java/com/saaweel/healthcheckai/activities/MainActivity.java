package com.saaweel.healthcheckai.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.saaweel.healthcheckai.R;
import com.saaweel.healthcheckai.fragments.MainFragment;

import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.Fragment;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashScreen.installSplashScreen(this);

        setContentView(R.layout.activity_main);

        changeFragment(new MainFragment());
    }

    /**
     * Este método se encarga de cambiar el fragmento que se muestra en pantalla
     * @param fragment El fragmento que se va a mostrar
     * @return void
     */
    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view, fragment).commit();
        findViewById(R.id.fragment_view).setVisibility(View.VISIBLE);
    }
}