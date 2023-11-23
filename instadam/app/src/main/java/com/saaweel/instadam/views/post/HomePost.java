package com.saaweel.instadam.views.post;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.poo.Post;
import com.saaweel.instadam.poo.User;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomePost extends RecyclerView.ViewHolder {

    private final CircleImageView avatar;

    private final TextView username;

    private final ImageView verified;

    private final ImageView image;

    private final TextView likes;

    private final TextView description;

    public HomePost(View view) {
        super(view);

        avatar = view.findViewById(R.id.avatar);
        username = view.findViewById(R.id.username);
        verified = view.findViewById(R.id.verified);
        image = view.findViewById(R.id.image);
        likes = view.findViewById(R.id.likes);
        description = view.findViewById(R.id.description);
    }

    public void setPostData(Post post) {
        User user = post.getUser();

        Picasso.get().load(user.getAvatar()).into(avatar);
        username.setText(user.getUsername());

        if (user.isVerified())
            verified.setVisibility(View.VISIBLE);

        Picasso.get().load(post.getImage()).into(image);

        likes.setText(post.getLikes() + " Me gustas");

        description.setText(post.getDescription());
    }
}
