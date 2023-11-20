package com.saaweel.instadam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    /*
     * Este método se encarga de cambiar el fragmento que se muestra en pantalla
     * @param fragment El fragmento que se va a mostrar
     * @return void
     */
    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, fragment).commit();
        findViewById(R.id.mainActivity).setVisibility(View.VISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos el menú de navegación
        bottomNavigation = findViewById(R.id.bottomNavigation);

        // Cargamos de primera el fragmento de inicio
        changeFragment(new Start());

        // En base a la opción elegida cambiamos al fragmento necesario
        bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.startItem) {
                changeFragment(new Start());
            } else if (item.getItemId() == R.id.searchItem) {
                changeFragment(new Search());
            } else if (item.getItemId() == R.id.cameraItem) {
                changeFragment(new Camera());
            } else if (item.getItemId() == R.id.notifyItem) {
                changeFragment(new Notify());
            } else if (item.getItemId() == R.id.profileItem) {
                changeFragment(new Profile());
            }

            return true;
        });
    }
}