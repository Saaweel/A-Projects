package com.saaweel.reciclaje;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final String [] images = {"organic", "organic", "paper", "paper", "plastic", "plastic", "vidrio", "vidrio"};
    int currentImage;

    ImageView showingImage;
    ImageView resultImage;

    @SuppressLint("DiscouragedApi")
    private void randomImage() {
        int random = (int)(Math.random() * (images.length - 1) + 1);

        showingImage.setImageResource(this.getResources().getIdentifier("foto_" + random, "drawable", this.getPackageName()));

        currentImage = random - 1;
    }

    private void clickedImageButton(String selected) {

        if (images[currentImage].equals(selected)) {
            resultImage.setImageResource(R.drawable.si);
        } else {
            resultImage.setImageResource(R.drawable.no);
        }

        resultImage.setVisibility(View.VISIBLE);

        new Handler().postDelayed(() -> {
            resultImage.setVisibility(View.INVISIBLE);
            randomImage();
        }, 1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showingImage = findViewById(R.id.showingImage);
        resultImage = findViewById(R.id.resultImage);

        randomImage();

        findViewById(R.id.organic).setOnClickListener(view -> clickedImageButton("organic"));
        findViewById(R.id.paper).setOnClickListener(view -> clickedImageButton("paper"));
        findViewById(R.id.plastic).setOnClickListener(view -> clickedImageButton("plastic"));
        findViewById(R.id.vidrio).setOnClickListener(view -> clickedImageButton("vidrio"));
    }
}