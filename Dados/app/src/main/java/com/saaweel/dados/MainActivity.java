package com.saaweel.dados;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("DiscouragedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button throwButton = findViewById(R.id.throwButton);
        ImageView diceResult = findViewById(R.id.diceResult);

        throwButton.setOnClickListener((e) -> {
            int random = (int)(Math.random() * 5 + 1);

            diceResult.setImageResource(this.getResources().getIdentifier("dado_" + random, "drawable", this.getPackageName()));
        });
    }
}