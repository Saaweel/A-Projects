package com.saaweel.instadam;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import com.squareup.picasso.Picasso;

public class HomePost extends RecyclerView.ViewHolder {
    private CircleImageView avatar;

    private TextView username;

    private ImageView verified;

    private ImageView image;

    private TextView likes;

    private TextView description;

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
