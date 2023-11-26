// Declaraciones de paquetes
package com.saaweel.instadam.views.notification;

// Declaraciones de librerías
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.models.Noti;
import com.saaweel.instadam.models.User;
import com.squareup.picasso.Picasso;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Clase Notification
 * Esta clase representa un elemento de vista en el adaptador de notificaciones.
 */
public class Notification extends RecyclerView.ViewHolder {
    private final CircleImageView avatar;
    private final TextView username;
    private final TextView verified;
    private final TextView notifyContent;
    private final ImageView postImage;

    /**
     * Constructor de la clase.
     * @param view Vista que representa un elemento de notificación.
     */
    public Notification(View view) {
        super(view);

        // Inicializar elementos de la vista
        avatar = view.findViewById(R.id.avatar);
        username = view.findViewById(R.id.username);
        verified = view.findViewById(R.id.verified);
        notifyContent = view.findViewById(R.id.notifyContent);
        postImage = view.findViewById(R.id.postImage);
    }

    /**
     * Método para establecer los datos de la notificación en la vista.
     * @param noti Objeto de notificación utilizado para obtener los datos.
     */
    public void setNotificationData(Noti noti) {
        User user = noti.getUser();

        // Cargar la imagen del avatar del usuario utilizando Picasso
        Picasso.get().load(user.getAvatar()).into(avatar);

        // Establecer el nombre de usuario en el TextView correspondiente
        this.username.setText(user.getUsername());

        // Mostrar el ícono de verificación si el usuario está verificado
        if (user.isVerified())
            this.verified.setVisibility(View.VISIBLE);

        // Configurar el contenido de la notificación con formato HTML
        this.notifyContent.setText(HtmlCompat.fromHtml("<span>" + noti.getContent() + " <span style='white-space:nowrap; color: gray'>" + this.getTextAgoText(noti.getDate()) + "</span></span>", HtmlCompat.FROM_HTML_MODE_COMPACT));

        // Obtener la URL de la imagen asociada a la notificación
        String image = noti.getImage();

        // Cargar la imagen de la notificación utilizando Picasso o ocultar el ImageView si no hay imagen
        if (image != null)
            Picasso.get().load(image).into(this.postImage);
        else
            this.postImage.setVisibility(View.GONE);
    }

    /**
     * Método privado para obtener el texto del tiempo transcurrido desde la notificación.
     * @param date Fecha de la notificación.
     * @return Texto del tiempo transcurrido en formato simplificado.
     */
    private String getTextAgoText(Date date) {
        long timeDifferenceMillis = new Date().getTime() - date.getTime();

        long seconds = timeDifferenceMillis / 1000 % 60;
        long minutes = timeDifferenceMillis / (60 * 1000) % 60;
        long hours = timeDifferenceMillis / (60 * 60 * 1000) % 24;
        long days = timeDifferenceMillis / (24 * 60 * 60 * 1000);

        if (days > 0) return days + " d";

        if (hours > 0) return hours + " h";

        if (minutes > 0) return minutes + " m";

        return seconds + " s";
    }
}
