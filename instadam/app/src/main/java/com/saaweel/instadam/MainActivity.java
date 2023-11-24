package com.saaweel.instadam;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.saaweel.instadam.fragments.Camera;
import com.saaweel.instadam.fragments.Home;
import com.saaweel.instadam.fragments.Notify;
import com.saaweel.instadam.fragments.Profile;
import com.saaweel.instadam.fragments.Search;
import com.saaweel.instadam.poo.Noti;
import com.saaweel.instadam.poo.Post;
import com.saaweel.instadam.poo.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;

    /*
     * Este método se encarga de cambiar el fragmento que se muestra en pantalla
     * @param fragment El fragmento que se va a mostrar
     * @return void
     */
    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivity, fragment).commit();
        findViewById(R.id.mainActivity).setVisibility(View.VISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Post> posts = new ArrayList<>();

        User user = new User("_saaweel_");
        user.setAvatar("https://scontent-mad1-1.cdninstagram.com/v/t51.2885-19/383839216_257979966663607_8135137719360071505_n.jpg?stp=dst-jpg_s320x320&_nc_ht=scontent-mad1-1.cdninstagram.com&_nc_cat=107&_nc_ohc=T4sKuG5ajsoAX-6SgvJ&edm=AOQ1c0wBAAAA&ccb=7-5&oh=00_AfArkFFTwvOG6cVAX850DMmSPHcYtKY9RR6vUF-KAoRhMA&oe=6562AA47&_nc_sid=8b3546");
        Post post = new Post(user, "https://th.bing.com/th/id/OIP.yoyT2HWplwAAzSG_EcZIkwHaE8?rs=1&pid=ImgDetMain");
        posts.add(post);

        user = new User("duki");
        user.setVerified(true);
        user.setAvatar("https://scontent-mad2-1.cdninstagram.com/v/t51.2885-19/374720732_135203229662888_2293027917940746544_n.jpg?stp=dst-jpg_s150x150&_nc_ht=scontent-mad2-1.cdninstagram.com&_nc_cat=1&_nc_ohc=BWTFFXgjbIoAX_BxzWw&edm=ABmJApABAAAA&ccb=7-5&oh=00_AfA3oOXe3xVtY5KBpV0Yx95PkjIBUhZJNn2EqNPufjkpAg&oe=6563A6C5&_nc_sid=b41fef");
        posts.add(new Post(user, "https://scontent-mad2-1.cdninstagram.com/v/t51.2885-15/402149041_704209018377717_7318977087116103029_n.heic?stp=dst-jpg_e35&efg=eyJ2ZW5jb2RlX3RhZyI6ImltYWdlX3VybGdlbi4xMzg3eDEzODcuc2RyIn0&_nc_ht=scontent-mad2-1.cdninstagram.com&_nc_cat=109&_nc_ohc=P43We30SaaAAX8Z0JSZ&edm=ACWDqb8BAAAA&ccb=7-5&ig_cache_key=MzIzNTU2MjExODE0NDM1MjUzMg%3D%3D.2-ccb7-5&oh=00_AfChWN1tjluQp1-2s5ZTf2-AVsAoKuWXpudoeStlAorJaQ&oe=6562EAC5&_nc_sid=ee9879"));

        posts.add(new Post(new User("jesustucci_"), "https://th.bing.com/th/id/R.99472a51b596c736f1703981dcbb9a10?rik=gbbtjTBAnWF8vA&riu=http%3a%2f%2fwww.hdcarwallpapers.com%2fwalls%2fmustang_shelby_gt350_4k-wide.jpg&ehk=siVB5D%2fRvNCdR7pn7fTjVF50e2Qg5W9wvLjfUIr2Kfg%3d&risl=&pid=ImgRaw&r=0"));

        user = new User("nicki.nicole");
        user.setVerified(true);
        posts.add(new Post(user, "https://th.bing.com/th/id/OIP.JWji_0qKHlCfVu_iw9qXxAHaE8?rs=1&pid=ImgDetMain"));

        ArrayList<Noti> notifications = new ArrayList<>();

        notifications.add(0, new Noti(user, post.getImage(), "Le ha gustado tu publicación"));
        notifications.add(0, new Noti(user, "Ha comenzado a seguirte"));

        bottomNavigation = findViewById(R.id.bottomNavigation);

        changeFragment(new Home(posts));

        bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.homeItem) {
                changeFragment(new Home(posts));
            } else if (item.getItemId() == R.id.searchItem) {
                changeFragment(new Search(posts));
            } else if (item.getItemId() == R.id.cameraItem) {
                changeFragment(new Camera());
            } else if (item.getItemId() == R.id.notifyItem) {
                changeFragment(new Notify(notifications));
            } else if (item.getItemId() == R.id.profileItem) {
                changeFragment(new Profile());
            }

            return true;
        });
    }
}