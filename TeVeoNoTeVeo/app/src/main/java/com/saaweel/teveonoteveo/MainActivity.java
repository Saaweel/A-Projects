package com.saaweel.teveonoteveo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switchSee = findViewById(R.id.switchSee);

        /* He utilizado image view pero con las imágenes guardadas en drawable  */
        ImageView imageResult = findViewById(R.id.imageResult);

        switchSee.setOnClickListener(View -> {
            if (switchSee.isChecked()) {
                switchSee.setText("Te veo");
                imageResult.setImageResource(R.drawable.seeu);
            } else {
                switchSee.setText("No te veo");
                imageResult.setImageResource(R.drawable.dontseeu);
            }
        });
    }
}