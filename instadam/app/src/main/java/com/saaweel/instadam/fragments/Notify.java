// Declaraciones de paquetes
package com.saaweel.instadam.fragments;

// Declaraciones de librerías
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.models.Noti;
import com.saaweel.instadam.views.notification.NotificationAdapter;

import java.util.ArrayList;

/**
 * Clase Notify
 * Esta clase es responsable de mostrar la vista de la sección de notificaciones.
 */
public class Notify extends Fragment {
    private ArrayList<Noti> notifications;

    /**
     * Constructor vacío requerido según la documentación de los fragmentos.
     */
    public Notify() {
        // Constructor vacío
    }

    /**
     * Constructor de la clase.
     * @param notifications Lista de notificaciones.
     */
    public Notify(ArrayList<Noti> notifications) {
        this.notifications = notifications;
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
        View view = inflater.inflate(R.layout.fragment_notify, container, false);

        NotificationAdapter customAdapter = new NotificationAdapter(this.notifications); // Crear adaptador de notificaciones
        RecyclerView recyclerView = view.findViewById(R.id.notifications); // Obtener vista de lista de notificaciones
        recyclerView.setAdapter(customAdapter); // Asignar adaptador de notificaciones a la vista de lista de notificaciones

        return view;
    }
}
