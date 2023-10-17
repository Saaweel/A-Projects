package com.example.practica2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvActivate = findViewById(R.id.tvActivate);
        tvActivate.setVisibility(View.VISIBLE);

        ToggleButton btnToggle = findViewById(R.id.btnToggle);
        btnToggle.setVisibility(View.VISIBLE);

        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnToggle.isChecked()){
                    tvActivate.setText("Activado");
                } else {
                    tvActivate.setText("Desactivado");
                }

            }
        });
    }
}