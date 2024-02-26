// Declaraciones de paquetes
package com.saaweel.instadam;

// Declaraciones de librerías
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.saaweel.instadam.activities.LoginActivity;
import com.saaweel.instadam.database.DBHelper;
import com.saaweel.instadam.fragments.Home;
import com.saaweel.instadam.fragments.Notify;
import com.saaweel.instadam.fragments.Profile;
import com.saaweel.instadam.fragments.Search;
import com.saaweel.instadam.lib.ImgurAPI;
import com.saaweel.instadam.models.Noti;
import com.saaweel.instadam.models.Post;
import com.saaweel.instadam.models.User;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase MainActivity
 * Esta clase es la encargada de mostrar la vista principal de la aplicación
 */
public class MainActivity extends AppCompatActivity {
    private ArrayList<User> users;
    private ArrayList<Post> posts;
    private ArrayList<Noti> notifications;
    private User myUser;

    /**
     * Instancia de la base de datos de Firebase.
     */
    FirebaseFirestore db;

    /**
     * Este método se encarga de cargar las notificaciones de
     * la base de datos local SQLite
     * @return void
     */
    private void loadNotifications() {
        this.notifications = new ArrayList<>();

        // Crear una instancia de la base de datos
        DBHelper dbHelper = new DBHelper(this);

        // Obtener las notificaciones de la base de datos
        // Estructura de la matriz: [usuario, imagen, contenido, fecha]
        String[][] notifications = dbHelper.getNotifications();

        // Si se obtuvieron notificaciones
        if (notifications != null) {
            // Recorrer las notificaciones
            for (String[] n : notifications) {
                User user = getUserFromUsername(n[0]); // Obtener el usuario de la notificación
                // Si la notificación tiene imagen
                if (!n[1].isEmpty()) {
                    // Añadir la notificación a la lista
                    this.notifications.add(new Noti(user, n[1], n[2], n[3]));
                } else {
                    // Añadir la notificación a la lista
                    this.notifications.add(new Noti(user, n[2], n[3]));
                }
            }
        }
    }

    /**
     * Este método se encarga de obtener un usuario a partir de un nombre de usuario
     * @param username El nombre de usuario
     * @return User El usuario
     */
    private User getUserFromUsername(String username) {
        // Recorrer todos los usuarios
        for (User u : this.users) {
            if (u.getUsername().equals(username)) { // Si el nombre de usuario coincide
                return u; // Devolver el usuario
            }
        }

        return null; // Si no se encontró el usuario
    }

    /**
     * Este método se encarga de cambiar el fragmento que se muestra en pantalla
     * @param fragment El fragmento que se va a mostrar
     * @return void
     */
    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, fragment).commit();
        findViewById(R.id.mainActivity).setVisibility(View.VISIBLE);
    }

    /**
     * Este método se encarga de subir una imagen a Imgur y publicarla en los posts
     * @param bitmap La imagen que se va a subir
     * @return void
     */
    private void uploadImage(Bitmap bitmap) {
        // Convertir la imagen a un array de bytes con formato PNG
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        byte[] bitmapdata = bos.toByteArray();

        // Crear una instancia de Retrofit para realizar la petición a la API de Imgur
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.imgur.com/3/").addConverterFactory(GsonConverterFactory.create()).build();

        // Crear una instancia de la interfaz de la API de Imgur para realizar la petición
        ImgurAPI imgurAPI = retrofit.create(ImgurAPI.class);

        // Crear el cuerpo de la petición con la imagen
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), bitmapdata);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", "uploadimage.png", requestFile);

        // Realizar la petición a la API de Imgur
        Call<ResponseBody> call = imgurAPI.uploadImage(body, "Client-ID 8357eb9841635c0");

        // Procesar la respuesta de la petición
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) { // Si la petición fue exitosa
                    try {
                        // Obtener el enlace de la imagen subida
                        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(response.body()).string());
                        String link = jsonObject.getJSONObject("data").getString("link");

                        // Añadir la publicación a la lista de publicaciones
                        posts.add(new Post(myUser, link));

                        Map<String, Object> postData = new HashMap<>();
                        postData.put("user", myUser.getUsername());
                        postData.put("image", link);
                        db.collection("posts").add(postData);

                        // Cambiar el fragmento a la vista de inicio
                        changeFragment(new Home(posts));
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getBaseContext(), "Hubo un error al subir la imagen", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Hubo un error al subir la imagen", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Toast.makeText(getBaseContext(), "Hubo un error al subir la imagen", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Este método se encarga de obtener las publicaciones de un usuario
     * @param user El usuario del que se van a obtener las publicaciones
     * @param allPosts La lista de todas las publicaciones
     * @return ArrayList<Post> La lista de publicaciones del usuario
     */
    private ArrayList<Post> getPostsFromUser(User user, ArrayList<Post> allPosts) {
        ArrayList<Post> posts = new ArrayList<>();

        // Recorrer todas las publicaciones
        for (Post p : allPosts) {
            if (p.getUser().equals(user)) // Si la publicación pertenece al usuario
                posts.add(p); // Añadir la publicación a la lista
        }

        return posts;
    }

    /**
     * Este método se encarga de cargar los usuarios y las publicaciones de la base de datos
     * @return void
     */
    private void loadData() {
        this.users = new ArrayList<>();

        this.db.collection("users").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult().getDocuments()) {
                    User user = new User(document.getString("username"));

                    String avatar = document.getString("avatar");
                    if (avatar != null) {
                        user.setAvatar(avatar);
                    }

                    user.setVerified(Boolean.TRUE.equals(document.getBoolean("verified")));

                    String followers = document.getString("followers");
                    if (followers != null) {
                        user.setFollowers(Integer.parseInt(followers));
                    }

                    String follows = document.getString("follows");
                    if (follows != null) {
                        user.setFollows(Integer.parseInt(follows));
                    }

                    this.users.add(user);
                }

                // Obtener el usuario actual
                this.myUser = getUserFromUsername(getIntent().getStringExtra("USERNAME"));

                if (this.myUser == null) {
                    Toast.makeText(this, "Ha ocurrido un error al cargar el usuario", Toast.LENGTH_SHORT).show();

                    getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().clear().apply();

                    startActivity(new Intent(this, LoginActivity.class));
                }
            }

            // Obtener las publicaciones de la base de datos
            this.posts = new ArrayList<>();

            this.db.collection("posts").get().addOnCompleteListener(task2 -> {
                if (task2.isSuccessful()) {
                    for (DocumentSnapshot document : task2.getResult().getDocuments()) {
                        User user = getUserFromUsername(document.getString("user"));

                        System.out.println(document.getString("user") + " " + document.getString("image"));
                        if (user != null) {
                            this.posts.add(new Post(user, document.getString("image")));
                        }
                    }
                }

                // Cambiar el fragmento a la vista de inicio
                changeFragment(new Home(this.posts));
            });
        });
    }

    /**
     * Este método se encarga de crear la vista principal de la aplicación
     * @param savedInstanceState Instancia guardada de la actividad
     * @return void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.db = FirebaseFirestore.getInstance();

        loadData();

        // Crear un ActivityResultLauncher para la cámara y manejar el resultado
        ActivityResultLauncher<Intent> activityCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            // Si la petición fue exitosa
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();

                // Si se obtuvo datos de la petición
                if (data != null) {
                    Bundle extras = data.getExtras();

                    // Si se obtuvo extras de la petición
                    if (extras != null) {

                        // Obtener la imagen de la petición
                        Bitmap imageBitmap = (Bitmap) extras.get("data");

                        if (imageBitmap != null) { // Si se obtuvo la imagen
                            // Subir la imagen a Imgur y publicarla en los posts
                            uploadImage(imageBitmap);
                        }
                    }
                }
            }
        });

        // Carga inicial de notificaciones para el contador de la barra de navegación inferior
        loadNotifications();

        // Obtener la barra de navegación inferior
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        // Mostrar el número de notificaciones en la barra de navegación inferior
        bottomNavigation.getOrCreateBadge(R.id.notifyItem).setNumber(this.notifications.size());

        // Manejar los eventos de la barra de navegación inferior
        bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.homeItem) { // Si se seleccionó la vista de inicio
                changeFragment(new Home(this.posts)); // Cambiar el fragmento a la vista de inicio

            } else if (item.getItemId() == R.id.searchItem) { // Si se seleccionó la vista de búsqueda
                changeFragment(new Search(this.posts, this.users)); // Cambiar el fragmento a la vista de búsqueda

            } else if (item.getItemId() == R.id.cameraItem) { // Si se seleccionó la vista de cámara
                // Comprobar si la aplicación tiene permiso para usar la cámara
                int permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) { // Si no tiene permiso
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 225); // Solicitar permiso
                } else {
                    // Crear un Intent para abrir la cámara
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    // Iniciar el ActivityResultLauncher para la cámara
                    activityCamera.launch(cameraIntent);
                }
            } else if (item.getItemId() == R.id.notifyItem) { // Si se seleccionó la vista de notificaciones
                loadNotifications(); // Cargar las notificaciones

                changeFragment(new Notify(this.notifications)); // Cambiar el fragmento a la vista de notificaciones

                bottomNavigation.getOrCreateBadge(R.id.notifyItem).setVisible(false); // Ocultar el número de notificaciones
            } else if (item.getItemId() == R.id.profileItem) { // Si se seleccionó la vista de perfil
                changeFragment(new Profile(myUser, getPostsFromUser(myUser, this.posts), true)); // Cambiar el fragmento a la vista de perfil
            }

            return true;
        });
    }
}