// Declaraciones de paquetes
package com.saaweel.instadam.fragments;

// Declaraciones de librerías
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.models.Post;
import com.saaweel.instadam.views.mini_post.MiniPostAdapter;

import java.util.ArrayList;

/**
 * Clase Search
 * Esta clase es responsable de mostrar la vista de la sección de búsqueda.
 */
public class Search extends Fragment {
    private ArrayList<Post> posts;

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
    public Search(ArrayList<Post> posts) {
        this.posts = posts;
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

        return view;
    }
}