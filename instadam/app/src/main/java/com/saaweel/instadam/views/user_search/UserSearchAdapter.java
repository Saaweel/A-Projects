// Declaraciones de paquetes
package com.saaweel.instadam.views.user_search;

// Declaraciones de librerías
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.models.User;

import java.util.ArrayList;

/**
 * Clase UserSearchAdapter
 * Este adaptador maneja la creación y actualización de las vistas de usuario en la pantalla de búsqueda de usuarios.
 */
public class UserSearchAdapter extends RecyclerView.Adapter<UserSearch> {

    private ArrayList<User> localDataSet;

    /**
     * Constructor de la clase.
     * @param dataSet Lista de objetos de usuarios a mostrar.
     */
    public UserSearchAdapter(ArrayList<User> dataSet) {
        localDataSet = dataSet;
    }

    /**
     * Método onCreateViewHolder
     * Este método se llama cuando se crean nuevas vistas de usuarios
     * @param viewGroup Grupo de vistas al que pertenece la nueva vista.
     * @param viewType Tipo de vista.
     * @return Nueva instancia de la clase UserSearch que representa una vista de usuarios.
     */
    @Override
    public UserSearch onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_user_search, viewGroup, false);
        return new UserSearch(view);
    }

    /**
     * Método onBindViewHolder
     * Este método se llama para actualizar una vista de usuarios con datos específicos.
     * @param user Vista de usuario que se actualizará.
     * @param position Posición en el conjunto de datos local.
     */
    @Override
    public void onBindViewHolder(UserSearch user, final int position) {
        user.setUserData(localDataSet.get(position));
    }

    /**
     * Método getItemCount
     * Este método devuelve el número total de elementos en el conjunto de datos local.
     * @return Número total de usuarios en el conjunto de datos local.
     */
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
