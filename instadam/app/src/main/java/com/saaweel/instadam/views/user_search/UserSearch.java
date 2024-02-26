// Declaraciones de paquetes
package com.saaweel.instadam.views.user_search;

// Declaraciones de librerías
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.models.User;
import com.squareup.picasso.Picasso;

/**
 * Clase UserSearch
 * Esta clase representa un elemento de vista en el adaptador de usuarios.
 */
public class UserSearch extends RecyclerView.ViewHolder {
    ImageView avatar;
    TextView username;
    TextView userDescription;
    TextView verified;

    /**
     * Constructor de la clase.
     * @param view Vista que representa un elemento de usuario.
     */
    public UserSearch(View view) {
        super(view);

        avatar = view.findViewById(R.id.avatar);
        username = view.findViewById(R.id.username);
        userDescription = view.findViewById(R.id.userDescription);
        verified = view.findViewById(R.id.verified);
    }

    /**
     * Método para establecer los datos del usuario en la vista.
     * @param user Objeto de usuario utilizado para obtener la imagen de la mini publicación.
     */
    public void setUserData(User user) {
        Picasso.get().load(user.getAvatar()).into(avatar);

        username.setText(user.getUsername());

        String followers = itemView.getContext().getString(R.string.followers);
        String follows = itemView.getContext().getString(R.string.follows);
        userDescription.setText(user.getFollowersString() + " " + followers + " • " + user.getFollowsString() + " " + follows);

        verified.setVisibility(user.isVerified() ? View.VISIBLE : View.INVISIBLE);
    }
}