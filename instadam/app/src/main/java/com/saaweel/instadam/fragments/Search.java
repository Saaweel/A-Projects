// Declaraciones de paquetes
package com.saaweel.instadam.fragments;

// Declaraciones de librerías
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.models.Post;
import com.saaweel.instadam.models.User;
import com.saaweel.instadam.views.mini_post.MiniPostAdapter;
import com.saaweel.instadam.views.user_search.UserSearchAdapter;

import java.util.ArrayList;

/**
 * Clase Search
 * Esta clase es responsable de mostrar la vista de la sección de búsqueda.
 */
public class Search extends Fragment {
    private ArrayList<Post> posts;
    private ArrayList<User> users;

    /**
     * Constructor vacío requerido según la documentación de los fragmentos.
     */
    public Search() {
        // Constructor vacío
    }

    /**
     * Constructor de la clase.
     * @param posts Lista de publicaciones utilizadas en la sección de búsqueda.
     */
    public Search(ArrayList<Post> posts, ArrayList<User> users) {
        this.posts = posts;
        this.users = users;
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.search_posts); // Obtener vista de lista de búsqueda
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3)); // Asignar administrador de diseño de cuadrícula a la vista de lista de búsqueda
        recyclerView.setAdapter(new MiniPostAdapter(this.posts)); // Asignar adaptador de mini publicaciones a la vista de lista de búsqueda

        EditText searchBar = view.findViewById(R.id.search_bar); // Obtener vista de barra de búsqueda

        searchBar.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(android.text.Editable s) {
                String text = s.toString();

                if (text.isEmpty()) {
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                    recyclerView.setAdapter(new MiniPostAdapter(posts));
                } else {
                    ArrayList<User> searchResults = new ArrayList<>();

                    for (User user : users) {
                        if (user.getUsername().toLowerCase().contains(text.toLowerCase())) {
                            searchResults.add(user);
                        }
                    }

                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
                    recyclerView.setAdapter(new UserSearchAdapter(searchResults));
                }
            }
        });

        return view;
    }
}