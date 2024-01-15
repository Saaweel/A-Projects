package com.saaweel.instadam.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.saaweel.instadam.MainActivity;
import com.saaweel.instadam.R;

public class LoginActivity extends AppCompatActivity {
    boolean login = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Debes inflar el diseño primero

        TextView changeText = findViewById(R.id.changeText);

        Button button = findViewById(R.id.button);

        EditText userField = findViewById(R.id.user);

        EditText emailField = findViewById(R.id.email);

        EditText passwordField = findViewById(R.id.password);

        TextView errorText = findViewById(R.id.error_message);

        changeText.setOnClickListener(v -> {
            login = !login;

            if (login) {
                button.setText(R.string.login);

                changeText.setText(R.string.register);

                emailField.setVisibility(View.GONE);
            } else {
                button.setText(R.string.register);

                changeText.setText(R.string.login);

                emailField.setVisibility(View.VISIBLE);
            }
        });

        button.setOnClickListener(v -> {
            String user = userField.getText().toString();
            String pass = passwordField.getText().toString();

            if (user.isEmpty() || pass.isEmpty()) {
                errorText.setText(R.string.must_complete_fields);
                errorText.setVisibility(View.VISIBLE);
                return;
            }

            if (login) {
                if (doLogin(user, pass)) {
                    Intent intent = new Intent(this, MainActivity.class);

                    intent.putExtra("USERNAME", user);

                    startActivity(intent);
                } else {
                    errorText.setText(R.string.incorrect_credentials);
                    errorText.setVisibility(View.VISIBLE);
                };
            } else {
                String email = emailField.getText().toString();


                if (email.isEmpty()) {
                    errorText.setText(R.string.must_complete_fields);
                    errorText.setVisibility(View.VISIBLE);
                    return;
                }

                if (doRegister(user, pass, email)) {
                    Intent intent = new Intent(this, MainActivity.class);

                    intent.putExtra("USERNAME", user);

                    startActivity(intent);
                } else {
                    errorText.setText(R.string.username_or_email_yet);
                    errorText.setVisibility(View.VISIBLE);
                };
            }
        });
    }

    private boolean doRegister(String user, String pass, String email) {
        return true;
    }

    private boolean doLogin(String user, String pass) {
        return true;
    }
}