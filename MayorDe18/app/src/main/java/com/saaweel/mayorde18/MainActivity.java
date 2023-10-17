package com.saaweel.mayorde18;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nameInput = findViewById(R.id.nameInput);

        EditText ageInput = findViewById(R.id.ageInput);

        ImageView imageResult = findViewById(R.id.imageResult);

        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(view -> {
            String name = nameInput.getText().toString();

            if (!name.isEmpty()) {
                int age;
                try {
                    age = Integer.parseInt(ageInput.getText().toString());

                    if (age < 18) {
                        imageResult.setImageResource(R.drawable.minor);
                        imageResult.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(),"No puedes ver el contenido " + name, Toast.LENGTH_LONG).show();
                    } else {
                        imageResult.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplicationContext(),"Eres mayor de edad", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Completa todos los campos", Toast.LENGTH_LONG).show();
                }
            } else
                Toast.makeText(getApplicationContext(),"Completa todos los campos", Toast.LENGTH_LONG).show();
        });
    }
}