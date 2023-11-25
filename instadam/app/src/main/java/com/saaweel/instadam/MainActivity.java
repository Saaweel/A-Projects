package com.saaweel.instadam;

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
import com.saaweel.instadam.fragments.Home;
import com.saaweel.instadam.fragments.Notify;
import com.saaweel.instadam.fragments.Profile;
import com.saaweel.instadam.fragments.Search;
import com.saaweel.instadam.lib.ImgurAPI;
import com.saaweel.instadam.models.Noti;
import com.saaweel.instadam.models.Post;
import com.saaweel.instadam.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Post> posts;

    private ArrayList<Noti> notifications;

    private User myUser;

    private void loadSimulatedData() {
        posts = new ArrayList<>();
        notifications = new ArrayList<>();

        this.myUser = new User("_saaweel_");

        this.myUser.setAvatar("https://scontent-mad1-1.cdninstagram.com/v/t51.2885-19/383839216_257979966663607_8135137719360071505_n.jpg?stp=dst-jpg_s320x320&_nc_ht=scontent-mad1-1.cdninstagram.com&_nc_cat=107&_nc_ohc=T4sKuG5ajsoAX-6SgvJ&edm=AOQ1c0wBAAAA&ccb=7-5&oh=00_AfArkFFTwvOG6cVAX850DMmSPHcYtKY9RR6vUF-KAoRhMA&oe=6562AA47&_nc_sid=8b3546");

        Post post = new Post(this.myUser, "https://th.bing.com/th/id/OIP.yoyT2HWplwAAzSG_EcZIkwHaE8?rs=1&pid=ImgDetMain");
        this.posts.add(post);

        User user = new User("duki");
        user.setVerified(true);
        user.setAvatar("https://scontent-mad2-1.cdninstagram.com/v/t51.2885-19/374720732_135203229662888_2293027917940746544_n.jpg?stp=dst-jpg_s150x150&_nc_ht=scontent-mad2-1.cdninstagram.com&_nc_cat=1&_nc_ohc=BWTFFXgjbIoAX_BxzWw&edm=ABmJApABAAAA&ccb=7-5&oh=00_AfA3oOXe3xVtY5KBpV0Yx95PkjIBUhZJNn2EqNPufjkpAg&oe=6563A6C5&_nc_sid=b41fef");
        this.posts.add(new Post(user, "https://scontent-mad2-1.cdninstagram.com/v/t51.2885-15/402149041_704209018377717_7318977087116103029_n.heic?stp=dst-jpg_e35&efg=eyJ2ZW5jb2RlX3RhZyI6ImltYWdlX3VybGdlbi4xMzg3eDEzODcuc2RyIn0&_nc_ht=scontent-mad2-1.cdninstagram.com&_nc_cat=109&_nc_ohc=P43We30SaaAAX8Z0JSZ&edm=ACWDqb8BAAAA&ccb=7-5&ig_cache_key=MzIzNTU2MjExODE0NDM1MjUzMg%3D%3D.2-ccb7-5&oh=00_AfChWN1tjluQp1-2s5ZTf2-AVsAoKuWXpudoeStlAorJaQ&oe=6562EAC5&_nc_sid=ee9879"));

        this.posts.add(new Post(new User("jesustucci_"), "https://th.bing.com/th/id/R.99472a51b596c736f1703981dcbb9a10?rik=gbbtjTBAnWF8vA&riu=http%3a%2f%2fwww.hdcarwallpapers.com%2fwalls%2fmustang_shelby_gt350_4k-wide.jpg&ehk=siVB5D%2fRvNCdR7pn7fTjVF50e2Qg5W9wvLjfUIr2Kfg%3d&risl=&pid=ImgRaw&r=0"));

        user = new User("nicki.nicole");
        user.setVerified(true);
        this.posts.add(new Post(user, "https://th.bing.com/th/id/OIP.JWji_0qKHlCfVu_iw9qXxAHaE8?rs=1&pid=ImgDetMain"));

        this.notifications.add(0, new Noti(user, post.getImage(), "Le ha gustado tu publicación"));
        this.notifications.add(0, new Noti(user, "Ha comenzado a seguirte"));
    }

    /*
     * Este método se encarga de cambiar el fragmento que se muestra en pantalla
     * @param fragment El fragmento que se va a mostrar
     * @return void
     */
    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, fragment).commit();
        findViewById(R.id.mainActivity).setVisibility(View.VISIBLE);
    }

    private void uploadImage(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        byte[] bitmapdata = bos.toByteArray();

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.imgur.com/3/").addConverterFactory(GsonConverterFactory.create()).build();

        ImgurAPI imgurAPI = retrofit.create(ImgurAPI.class);

        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), bitmapdata);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", "uploadimage.png", requestFile);

        Call<ResponseBody> call = imgurAPI.uploadImage(body, "Client-ID 8357eb9841635c0");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(response.body()).string());
                        String link = jsonObject.getJSONObject("data").getString("link");

                        posts.add(new Post(myUser, link));
                    } catch (JSONException | IOException e) {
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

    private ArrayList<Post> getPostsFromUser(User user, ArrayList<Post> allPosts) {
        ArrayList<Post> posts = new ArrayList<>();

        for (Post p : allPosts) {
            if (p.getUser().equals(user))
                posts.add(p);
        }

        return posts;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityResultLauncher<Intent> activityCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();

                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap imageBitmap = (Bitmap) extras.get("data");

                        if (imageBitmap != null) {
                            uploadImage(imageBitmap);
                        }
                    }
                }
            }
        });

        loadSimulatedData();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.getOrCreateBadge(R.id.notifyItem).setNumber(this.notifications.size());

        changeFragment(new Home(this.posts));

        bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.homeItem) {
                changeFragment(new Home(this.posts));
            } else if (item.getItemId() == R.id.searchItem) {
                changeFragment(new Search(this.posts));
            } else if (item.getItemId() == R.id.cameraItem) {
                int permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 225);
                } else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    activityCamera.launch(cameraIntent);
                }
            } else if (item.getItemId() == R.id.notifyItem) {
                changeFragment(new Notify(this.notifications));
                bottomNavigation.getOrCreateBadge(R.id.notifyItem).setVisible(false);
            } else if (item.getItemId() == R.id.profileItem) {
                changeFragment(new Profile(myUser, getPostsFromUser(myUser, this.posts), true));
            }

            return true;
        });
    }
}