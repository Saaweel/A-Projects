package com.saaweel.instadam.views.mini_post;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.poo.Post;
import com.squareup.picasso.Picasso;

public class MiniPost extends RecyclerView.ViewHolder {
    private final ImageView image;

    public MiniPost(View view) {
        super(view);

        image = view.findViewById(R.id.image_mini_post);
    }

    public void setPostData(Post post) {
        Picasso.get().load(post.getImage()).into(image);
    }
}
