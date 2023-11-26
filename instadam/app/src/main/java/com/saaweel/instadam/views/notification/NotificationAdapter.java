// Declaraciones de paquetes
package com.saaweel.instadam.views.notification;

// Declaraciones de librerías
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.models.Noti;

import java.util.ArrayList;

/**
 * Clase NotificationAdapter
 * Este adaptador maneja la creación y actualización de las vistas de notificación en un RecyclerView.
 */
public class NotificationAdapter extends RecyclerView.Adapter<Notification> {

    private final ArrayList<Noti> localDataSet;

    /**
     * Constructor de la clase.
     * @param dataSet Lista de objetos de notificación utilizados para mostrar notificaciones.
     */
    public NotificationAdapter(ArrayList<Noti> dataSet) {
        localDataSet = dataSet;
    }

    /**
     * Método onCreateViewHolder
     * Este método se llama cuando se crean nuevas vistas de notificación.
     * @param viewGroup Grupo de vistas al que pertenece la nueva vista.
     * @param viewType Tipo de vista.
     * @return Nueva instancia de la clase Notification que representa una vista de notificación.
     */
    @NonNull
    @Override
    public Notification onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_notification, viewGroup, false);
        return new Notification(view);
    }

    /**
     * Método onBindViewHolder
     * Este método se llama para actualizar una vista de notificación con datos específicos.
     * @param notification Vista de notificación que se actualizará.
     * @param position Posición en el conjunto de datos local.
     */
    @Override
    public void onBindViewHolder(Notification notification, final int position) {
        notification.setNotificationData(localDataSet.get(position));
    }

    /**
     * Método getItemCount
     * Este método devuelve el número total de elementos en el conjunto de datos local.
     * @return Número total de notificaciones en el conjunto de datos local.
     */
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}