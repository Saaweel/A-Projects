// Declaraciones de paquetes
package com.saaweel.instadam.activities;

// Declaraciones de librerías

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.saaweel.instadam.MainActivity;
import com.saaweel.instadam.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase LoginActivity
 * Esta clase representa la funcionalidad de inicio de sesión de la aplicación. Extiende AppCompatActivity
 * y proporciona una interfaz de usuario para que los usuarios inicien sesión o se registren.
 */
public class LoginActivity extends AppCompatActivity {
    /**
     * Instancia de la base de datos de Firebase.
     */
    FirebaseFirestore db;

    /**
     * Indica si el estado actual es para inicio de sesión (true) o registro (false).
     */
    boolean login = true;

    /**
     * Se llama cuando se crea la actividad. Inicializa los elementos de la interfaz de usuario,
     * configura los escuchadores de eventos y maneja el inicio de sesión o el registro según la
     * entrada del usuario.
     *
     * @param savedInstanceState El estado de la instancia guardada de la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("LOGIN_USERNAME", null) != null) {
            // Redirigir a MainActivity si ya hay un usuario conectado
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("USERNAME", getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("LOGIN_USERNAME", null));
            startActivity(intent);
            return;
        }

        setContentView(R.layout.activity_login);

        // Elementos de la interfaz de usuario
        TextView changeText = findViewById(R.id.changeText);
        Button button = findViewById(R.id.button);
        EditText userField = findViewById(R.id.user);
        EditText emailField = findViewById(R.id.email);
        EditText passwordField = findViewById(R.id.password);
        TextView errorText = findViewById(R.id.error_message);

        // Cambiar entre las pantallas de inicio de sesión y registro
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

        // Iniciar el proceso de inicio de sesión o registro
        button.setOnClickListener(v -> {
            String user = userField.getText().toString();
            String pass = passwordField.getText().toString();

            if (user.isEmpty() || pass.isEmpty()) {
                errorText.setText(R.string.must_complete_fields);
                errorText.setVisibility(View.VISIBLE);
                return;
            }

            if (login) {
                doLogin(user, pass, errorText);
            } else {
                String email = emailField.getText().toString();

                if (email.isEmpty()) {
                    errorText.setText(R.string.must_complete_fields);
                    errorText.setVisibility(View.VISIBLE);
                    return;
                }
                doRegister(user, pass, email, errorText);
                if (false) {
                    // Redirigir a MainActivity en caso de registro exitoso
                    Intent intent = new Intent(this, MainActivity.class);

                    intent.putExtra("USERNAME", user);

                    // Guarda el nombre de usuario y contraseña en el sharedPreferences
                    getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putString("LOGIN_USERNAME", user).putString("LOGIN_PASSWORD", pass).apply();

                    startActivity(intent);
                } else {
                    errorText.setText(R.string.username_or_email_yet);
                    errorText.setVisibility(View.VISIBLE);
                }
            }
        });

        FirebaseApp.initializeApp(this);

        this.db = FirebaseFirestore.getInstance();
    }

    /**
     * Proceso de registro y devuelve un booleano que indica si el registro fue exitoso.
     *
     * @param user  El nombre de usuario proporcionado por el usuario durante el registro.
     * @param pass  La contraseña proporcionada por el usuario durante el registro.
     * @param email El correo electrónico proporcionado por el usuario durante el registro.
     * @param errorText El TextView que muestra el mensaje de error.
     * @return void
     */
    private void doRegister(String user, String pass, String email, TextView errorText) {
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
                                errorText.setText(R.string.username_or_email_yet);
                                errorText.setVisibility(View.VISIBLE);
                            }
                        } else {
                            errorText.setText(R.string.error);
                            errorText.setVisibility(View.VISIBLE);
                        }
                    });
                } else {
                    errorText.setText(R.string.username_or_email_yet);
                    errorText.setVisibility(View.VISIBLE);
                }
            } else {
                errorText.setText(R.string.error);
                errorText.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * Proceso de inicio de sesión y devuelve un booleano que indica si el inicio de sesión fue exitoso.
     *
     * @param user El nombre de usuario proporcionado por el usuario durante el inicio de sesión.
     * @param pass La contraseña proporcionada por el usuario durante el inicio de sesión.
     * @param errorText El TextView que muestra el mensaje de error.
     * @return void
     */
    private void doLogin(String user, String pass, TextView errorText) {
        this.db.collection("users").whereEqualTo("username", user).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (!task.getResult().isEmpty()) {
                    DocumentSnapshot document = task.getResult().getDocuments().get(0); // Obtén el primer documento (debería haber solo uno)
                    if (document.getString("password").equals(pass)) {
                        // Guarda el nombre de usuario y contraseña en el sharedPreferences
                        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putString("LOGIN_USERNAME", user).putString("LOGIN_PASSWORD", pass).apply();

                        // Redirigir a MainActivity en caso de inicio de sesión exitoso
                        Intent intent = new Intent(this, MainActivity.class);
                        intent.putExtra("USERNAME", user);
                        startActivity(intent);
                    } else {
                        errorText.setText(R.string.incorrect_credentials);
                        errorText.setVisibility(View.VISIBLE);
                    }
                } else { // Usuario no encontrado
                    errorText.setText(R.string.incorrect_credentials);
                    errorText.setVisibility(View.VISIBLE);
                }
            } else { // Error al buscar el usuario
                errorText.setText(R.string.error);
                errorText.setVisibility(View.VISIBLE);
            }
        });
    }
}