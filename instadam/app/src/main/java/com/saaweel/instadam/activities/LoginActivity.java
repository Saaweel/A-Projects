// Declaraciones de paquetes
package com.saaweel.instadam.activities;

// Declaraciones de librerías
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.saaweel.instadam.MainActivity;
import com.saaweel.instadam.R;

/**
 * Clase LoginActivity
 * Esta clase representa la funcionalidad de inicio de sesión de la aplicación. Extiende AppCompatActivity
 * y proporciona una interfaz de usuario para que los usuarios inicien sesión o se registren.
 */
public class LoginActivity extends AppCompatActivity {

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
                if (doLogin(user, pass)) {
                    // Redirigir a MainActivity en caso de inicio de sesión exitoso
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("USERNAME", user);
                    startActivity(intent);
                } else {
                    errorText.setText(R.string.incorrect_credentials);
                    errorText.setVisibility(View.VISIBLE);
                }
            } else {
                String email = emailField.getText().toString();

                if (email.isEmpty()) {
                    errorText.setText(R.string.must_complete_fields);
                    errorText.setVisibility(View.VISIBLE);
                    return;
                }

                if (doRegister(user, pass, email)) {
                    // Redirigir a MainActivity en caso de registro exitoso
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("USERNAME", user);
                    startActivity(intent);
                } else {
                    errorText.setText(R.string.username_or_email_yet);
                    errorText.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * Proceso de registro y devuelve un booleano que indica si el registro fue exitoso.
     *
     * @param user  El nombre de usuario proporcionado por el usuario durante el registro.
     * @param pass  La contraseña proporcionada por el usuario durante el registro.
     * @param email El correo electrónico proporcionado por el usuario durante el registro.
     * @return boolean Devuelve true si el registro es exitoso; de lo contrario, devuelve false.
     */
    private boolean doRegister(String user, String pass, String email) {
        // TODO: Validar si los datos cumplen el formato correcto y en ese caso insertarlos en la base de datos
        return true;
    }

    /**
     * Proceso de inicio de sesión y devuelve un booleano que indica si el inicio de sesión fue exitoso.
     *
     * @param user El nombre de usuario proporcionado por el usuario durante el inicio de sesión.
     * @param pass La contraseña proporcionada por el usuario durante el inicio de sesión.
     * @return boolean Devuelve true si el inicio de sesión es exitoso; de lo contrario, devuelve false.
     */
    private boolean doLogin(String user, String pass) {
        // TODO: Validar si los datos coinciden con los de la base de datos
        return true;
    }
}