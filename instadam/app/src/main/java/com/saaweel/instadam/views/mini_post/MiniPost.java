// Declaraciones de paquetes
package com.saaweel.instadam.views.mini_post;

// Declaraciones de librerías
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.models.Post;
import com.squareup.picasso.Picasso;

/**
 * Clase MiniPost
 * Esta clase representa un elemento de vista en el adaptador de mini publicaciones.
 */
public class MiniPost extends RecyclerView.ViewHolder {
    private final ImageView image;

    /**
     * Constructor de la clase.
     * @param view Vista que representa un elemento de mini publicación.
     */
    public MiniPost(View view) {
        super(view);

        // Inicializar elementos de la vista
        image = view.findViewById(R.id.image_mini_post);

        FrameLayout layout = view.findViewById(R.id.layout_mini_post);

        // Configurar el tamaño del elemento de mini publicación en función de la pantalla
        ViewGroup.LayoutParams layoutParams = layout.getLayoutParams();
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) view.getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        layoutParams.width = metrics.widthPixels / 3;
        layoutParams.height = metrics.widthPixels / 3;
        layout.setLayoutParams(layoutParams);
    }

    /**
     * Método para establecer los datos de la mini publicación en la vista.
     * @param post Objeto de publicación utilizado para obtener la imagen de la mini publicación.
     */
    public void setPostData(Post post) {
        Picasso.get().load(post.getImage()).into(image);
    }
}
