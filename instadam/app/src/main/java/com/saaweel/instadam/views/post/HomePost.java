// Declaraciones de paquetes
package com.saaweel.instadam.views.post;

// Declaraciones de librerías
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.models.Post;
import com.saaweel.instadam.models.User;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Clase HomePost
 * Esta clase representa un elemento de vista en el adaptador de publicaciones de inicio.
 */
public class HomePost extends RecyclerView.ViewHolder {

    private final CircleImageView avatar;
    private final TextView username;
    private final TextView verified;
    private final ImageView image;
    private final TextView likes;
    private final TextView description;

    /**
     * Constructor de la clase.
     * @param view Vista que representa un elemento de publicación de inicio.
     */
    public HomePost(View view) {
        super(view);

        // Inicializar elementos de la vista
        avatar = view.findViewById(R.id.avatar);
        username = view.findViewById(R.id.username);
        verified = view.findViewById(R.id.verified);
        image = view.findViewById(R.id.image);
        likes = view.findViewById(R.id.likes);
        description = view.findViewById(R.id.description);
    }

    /**
     * Método para establecer los datos de la publicación de inicio en la vista.
     * @param post Objeto de publicación utilizado para obtener los datos.
     */
    public void setPostData(Post post) {
        User user = post.getUser();

        // Cargar la imagen del avatar del usuario utilizando Picasso
        Picasso.get().load(user.getAvatar()).into(avatar);

        // Establecer el nombre de usuario en el TextView correspondiente
        username.setText(user.getUsername());

        // Mostrar el ícono de verificación si el usuario está verificado
        if (user.isVerified())
            verified.setVisibility(View.VISIBLE);

        // Cargar la imagen de la publicación utilizando Picasso
        Picasso.get().load(post.getImage()).into(image);

        // Formatear y mostrar el número de "me gusta" con separadores de mil
        String count = new DecimalFormat("#,###", new DecimalFormatSymbols(Locale.getDefault())).format(post.getLikes());
        String likesString = likes.getContext().getString(R.string.likes_format, count);
        likes.setText(likesString);

        // Establecer la descripción de la publicación en el TextView correspondiente
        description.setText(post.getDescription());
    }
}
