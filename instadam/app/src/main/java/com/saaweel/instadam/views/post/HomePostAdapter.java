// Declaraciones de paquetes
package com.saaweel.instadam.views.post;

// Declaraciones de librerías
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.models.Post;

import java.util.ArrayList;

/**
 * Clase HomePostAdapter
 * Este adaptador maneja la creación y actualización de las vistas de publicaciones de inicio en un RecyclerView.
 */
public class HomePostAdapter extends RecyclerView.Adapter<HomePost> {

    private final ArrayList<Post> localDataSet;

    /**
     * Constructor de la clase.
     * @param dataSet Lista de objetos de publicación utilizados para mostrar publicaciones de inicio.
     */
    public HomePostAdapter(ArrayList<Post> dataSet) {
        localDataSet = dataSet;
    }

    /**
     * Método onCreateViewHolder
     * Este método se llama cuando se crean nuevas vistas de publicación de inicio.
     * @param viewGroup Grupo de vistas al que pertenece la nueva vista.
     * @param viewType Tipo de vista.
     * @return Nueva instancia de la clase HomePost que representa una vista de publicación de inicio.
     */
    @NonNull
    @Override
    public HomePost onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_post, viewGroup, false);
        return new HomePost(view);
    }

    /**
     * Método onBindViewHolder
     * Este método se llama para actualizar una vista de publicación de inicio con datos específicos.
     * @param homePost Vista de publicación de inicio que se actualizará.
     * @param position Posición en el conjunto de datos local.
     */
    @Override
    public void onBindViewHolder(HomePost homePost, final int position) {
        homePost.setPostData(localDataSet.get(position));
    }

    /**
     * Método getItemCount
     * Este método devuelve el número total de elementos en el conjunto de datos local.
     * @return Número total de publicaciones de inicio en el conjunto de datos local.
     */
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}