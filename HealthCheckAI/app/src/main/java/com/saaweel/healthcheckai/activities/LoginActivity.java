package com.saaweel.healthcheckai.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.saaweel.healthcheckai.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private boolean login = true;
    private TextView title;
    private Button alterButton;
    private Button sendButton;
    private EditText userField;
    private EditText emailField;
    private EditText passwordField;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashScreen.installSplashScreen(this);

        if (getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("LOGIN_USERNAME", null) != null) {
            // Redirigir a MainActivity si ya hay un usuario conectado
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("USERNAME", getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("LOGIN_USERNAME", null));
            startActivity(intent);
            return;
        }

        setContentView(R.layout.activity_login);

        // Elementos de la interfaz de usuario
        this.title = findViewById(R.id.title);
        this.alterButton = findViewById(R.id.logout);
        this.sendButton = findViewById(R.id.sendButton);
        this.userField = findViewById(R.id.username);
        this.emailField = findViewById(R.id.email);
        this.passwordField = findViewById(R.id.password);

        // Cambiar entre las pantallas de inicio de sesión y registro
        alterButton.setOnClickListener(v -> {
            login = !login;

            if (login) {
                title.setText(R.string.login);
                sendButton.setText(R.string.login);
                alterButton.setText(R.string.register);
                emailField.setVisibility(View.GONE);
            } else {
                title.setText(R.string.register);
                sendButton.setText(R.string.register);
                alterButton.setText(R.string.login);
                emailField.setVisibility(View.VISIBLE);
            }
        });

        // Iniciar el proceso de inicio de sesión o registro
        sendButton.setOnClickListener(v -> {
            String user = userField.getText().toString();
            String pass = hashPassword(passwordField.getText().toString());

            userField.setError(null);
            passwordField.setError(null);
            emailField.setError(null);

            if (user.isEmpty()) {
                userField.setError(getString(R.string.must_enter_username));
                return;
            } else if (pass.isEmpty()) {
                passwordField.setError(getString(R.string.must_enter_password));
                return;
            }

            if (login) {
                doLogin(user, pass);
            } else {
                String email = emailField.getText().toString();

                if (email.isEmpty()) {
                    emailField.setError(getString(R.string.must_enter_email));
                    return;
                }

                if (user.length() < 6) {
                    userField.setError(getString(R.string.username_must_be_at_least_6_characters));
                    return;
                }

                if (pass.length() < 8) {
                    passwordField.setError(getString(R.string.password_must_be_at_least_8_characters));
                    return;
                }

                if (!pass.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")) {
                    passwordField.setError(getString(R.string.password_must_contain_number_letter_special));
                    return;
                }

                if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailField.setError(getString(R.string.invalid_email_address));
                    return;
                }

                doRegister(user, pass, email);
            }
        });

        FirebaseApp.initializeApp(this);

        this.db = FirebaseFirestore.getInstance();
    }

    /**
     * Devuelve la contraseña cifrada.
     * @param password La contraseña a cifrar.
     * @return La contraseña cifrada.
     */
    public static String hashPassword(String password) {
        return Integer.toString(password.hashCode(), 16);
    }

    /**
     * Proceso de registro y devuelve un booleano que indica si el registro fue exitoso.
     *
     * @param user  El nombre de usuario proporcionado por el usuario durante el registro.
     * @param pass  La contraseña proporcionada por el usuario durante el registro.
     * @param email El correo electrónico proporcionado por el usuario durante el registro.
     */
    private void doRegister(String user, String pass, String email) {
        this.db.collection("users").whereEqualTo("username", user).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult().isEmpty()) {
                    this.db.collection("users").whereEqualTo("email", email).get().addOnCompleteListener(task2 -> {
                        if (task2.isSuccessful()) {
                            if (task2.getResult().isEmpty()) {
                                Map<String, Object> userToInsert = new HashMap<>();

                                userToInsert.put("username", user);
                                userToInsert.put("password", pass);
                                userToInsert.put("email", email);

                                this.db.collection("users").add(userToInsert);

                                // Guarda el nombre de usuario y contraseña en el sharedPreferences
                                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putString("LOGIN_USERNAME", user).putString("LOGIN_PASSWORD", pass).apply();

                                // Redirigir a MainActivity en caso de registro exitoso
                                Intent intent = new Intent(this, MainActivity.class);
                                intent.putExtra("USERNAME", user);
                                startActivity(intent);
                            } else {
                                userField.setError(getString(R.string.username_or_email_already_exists));
                                emailField.setError(getString(R.string.username_or_email_already_exists));
                            }
                        } else {
                            userField.setError(getString(R.string.unexpected_error));
                            passwordField.setError(getString(R.string.unexpected_error));

                            if (!login) {
                                emailField.setError(getString(R.string.unexpected_error));
                            }
                        }
                    });
                } else {
                    userField.setError(getString(R.string.username_or_email_already_exists));
                    emailField.setError(getString(R.string.username_or_email_already_exists));
                }
            } else {
                userField.setError(getString(R.string.unexpected_error));
                passwordField.setError(getString(R.string.unexpected_error));

                if (!login) {
                    emailField.setError(getString(R.string.unexpected_error));
                }
            }
        });
    }

    /**
     * Proceso de inicio de sesión y devuelve un booleano que indica si el inicio de sesión fue exitoso.
     *
     * @param user El nombre de usuario proporcionado por el usuario durante el inicio de sesión.
     * @param pass La contraseña proporcionada por el usuario durante el inicio de sesión.
     */
    private void doLogin(String user, String pass) {
        this.db.collection("users").whereEqualTo("username", user).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (!task.getResult().isEmpty()) {
                    DocumentSnapshot document = task.getResult().getDocuments().get(0); // Obtén el primer documento (debería haber solo uno)
                    if (Objects.equals(document.getString("password"), pass)) {
                        // Guarda el nombre de usuario y contraseña en el sharedPreferences
                        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putString("LOGIN_USERNAME", user).putString("LOGIN_PASSWORD", pass).apply();

                        // Redirigir a MainActivity en caso de inicio de sesión exitoso
                        Intent intent = new Intent(this, MainActivity.class);
                        intent.putExtra("USERNAME", user);
                        startActivity(intent);
                    } else {
                        userField.setError(getString(R.string.invalid_credentials));
                        passwordField.setError(getString(R.string.invalid_credentials));
                    }
                } else { // Usuario no encontrado
                    userField.setError(getString(R.string.invalid_credentials));
                    passwordField.setError(getString(R.string.invalid_credentials));
                }
            } else { // Error al buscar el usuario
                userField.setError(getString(R.string.unexpected_error));
                passwordField.setError(getString(R.string.unexpected_error));

                if (!login) {
                    emailField.setError(getString(R.string.unexpected_error));
                }
            }
        });
    }
}