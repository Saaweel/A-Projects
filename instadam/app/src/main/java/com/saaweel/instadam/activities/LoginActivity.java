// Declaraciones de paquetes
package com.saaweel.instadam.activities;

// Declaraciones de librerías
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.saaweel.instadam.MainActivity;
import com.saaweel.instadam.R;
import com.saaweel.instadam.database.DBFrame;
import com.saaweel.instadam.database.DBHelper;

/**
 * Clase LoginActivity
 * Esta clase representa la funcionalidad de inicio de sesión de la aplicación. Extiende AppCompatActivity
 * y proporciona una interfaz de usuario para que los usuarios inicien sesión o se registren.
 */
public class LoginActivity extends AppCompatActivity {
    private DBHelper dbHelper;

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

        this.dbHelper = new DBHelper(this);
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
        Cursor cursor = this.dbHelper.getReadableDatabase().query(
                DBFrame.TABLE_USERS,
                new String[]{DBFrame.TABLE_USERS_USERNAME, DBFrame.TABLE_USERS_EMAIL},
                DBFrame.TABLE_USERS_USERNAME + " = ? OR " + DBFrame.TABLE_USERS_EMAIL + " = ?",
                new String[]{user, email},
                null,
                null,
                null
        );

        if (cursor.getCount() > 0) {
            return false;
        }

        ContentValues values = new ContentValues();

        values.put("username", user);
        values.put("email", email);
        values.put("password", pass);

        this.dbHelper.getWritableDatabase().insert(DBFrame.TABLE_USERS, null, values);

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
        Cursor cursor = this.dbHelper.getReadableDatabase().query(
                DBFrame.TABLE_USERS,
                new String[]{DBFrame.TABLE_USERS_USERNAME, DBFrame.TABLE_USERS_PASSWORD},
                DBFrame.TABLE_USERS_USERNAME + " = ? AND " + DBFrame.TABLE_USERS_PASSWORD + " = ?",
                new String[]{user, pass},
                null,
                null,
                null
        );

        return cursor.getCount() > 0;
    }
}