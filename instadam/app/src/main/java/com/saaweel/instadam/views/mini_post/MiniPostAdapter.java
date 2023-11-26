// Declaraciones de paquetes
package com.saaweel.instadam.views.mini_post;

// Declaraciones de librerías
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.models.Post;

import java.util.ArrayList;

/**
 * Clase MiniPostAdapter
 * Este adaptador maneja la creación y actualización de las vistas de mini publicaciones en un RecyclerView.
 */
public class MiniPostAdapter extends RecyclerView.Adapter<MiniPost> {

    private ArrayList<Post> localDataSet;

    /**
     * Constructor de la clase.
     * @param dataSet Lista de objetos de publicación utilizados para mostrar mini publicaciones.
     */
    public MiniPostAdapter(ArrayList<Post> dataSet) {
        localDataSet = dataSet;
    }

    /**
     * Método onCreateViewHolder
     * Este método se llama cuando se crean nuevas vistas de mini publicación.
     * @param viewGroup Grupo de vistas al que pertenece la nueva vista.
     * @param viewType Tipo de vista.
     * @return Nueva instancia de la clase MiniPost que representa una vista de mini publicación.
     */
    @Override
    public MiniPost onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_mini_post, viewGroup, false);
        return new MiniPost(view);
    }

    /**
     * Método onBindViewHolder
     * Este método se llama para actualizar una vista de mini publicación con datos específicos.
     * @param miniPost Vista de mini publicación que se actualizará.
     * @param position Posición en el conjunto de datos local.
     */
    @Override
    public void onBindViewHolder(MiniPost miniPost, final int position) {
        miniPost.setPostData(localDataSet.get(position));
    }

    /**
     * Método getItemCount
     * Este método devuelve el número total de elementos en el conjunto de datos local.
     * @return Número total de mini publicaciones en el conjunto de datos local.
     */
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
