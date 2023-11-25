// Declaraciones de paquetes
package com.saaweel.instadam.fragments;

// Declaraciones de librerías
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.models.Post;
import com.saaweel.instadam.models.User;
import com.saaweel.instadam.views.mini_post.MiniPostAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Clase Profile
 * Esta clase es responsable de mostrar la vista del perfil de usuario.
 */
public class Profile extends Fragment {
    private User user;
    private ArrayList<Post> posts;
    private boolean isMyProfile;

    /**
     * Constructor vacío requerido según la documentación de los fragmentos.
     */
    public Profile() {
        // Constructor vacío
    }

    /**
     * Constructor de la clase.
     * @param user     Usuario cuyo perfil se muestra.
     * @param posts    Lista de publicaciones del usuario.
     */
    public Profile(User user, ArrayList<Post> posts) {
        this.user = user;
        this.posts = posts;
    }

    /**
     * Constructor de la clase.
     * @param user         Usuario cuyo perfil se muestra.
     * @param posts        Lista de publicaciones del usuario.
     * @param isMyProfile  Indica si el perfil es del propio usuario.
     */
    public Profile(User user, ArrayList<Post> posts, boolean isMyProfile) {
        this.user = user;
        this.posts = posts;
        this.isMyProfile = isMyProfile;
    }

    /**
     * Método onCreate
     * Este método es responsable de crear la vista del fragmento.
     * @param savedInstanceState Instancia guardada.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Método onCreateView
     * Este método es responsable de crear la vista del fragmento.
     * @param inflater Inflador de vistas.
     * @param container Contenedor de vistas.
     * @param savedInstanceState Instancia guardada.
     * @return Vista del fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.profile_posts); // Obtener vista de lista de publicaciones
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3)); // Asignar administrador de diseño de cuadrícula a la vista de lista de publicaciones
        recyclerView.setAdapter(new MiniPostAdapter(this.posts)); // Asignar adaptador de publicaciones a la vista de lista de publicaciones

        TextView profileUsername = view.findViewById(R.id.profileUsername);
        profileUsername.setText(this.user.getUsername()); // Asignar nombre de usuario a la vista de nombre de usuario

        ImageView avatar = view.findViewById(R.id.avatar);
        Picasso.get().load(this.user.getAvatar()).into(avatar); // Asignar avatar a la vista de avatar

        TextView postCount = view.findViewById(R.id.postCount);
        postCount.setText(String.valueOf(this.posts.size())); // Asignar cantidad de publicaciones a la vista de cantidad de publicaciones

        TextView followerCount = view.findViewById(R.id.followerConut);
        followerCount.setText(String.valueOf(this.user.getFollowers())); // Asignar cantidad de seguidores a la vista de cantidad de seguidores

        TextView followsCount = view.findViewById(R.id.followsConut);
        followsCount.setText(String.valueOf(this.user.getFollows())); // Asignar cantidad de seguidos a la vista de cantidad de seguidos

        if (isMyProfile) { // Si el perfil es del propio usuario
            Button followButton = view.findViewById(R.id.followButton);
            followButton.setVisibility(View.INVISIBLE); // Ocultar botón de seguir
        }

        return view;
    }
}
