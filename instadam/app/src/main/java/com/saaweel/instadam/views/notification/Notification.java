package com.saaweel.instadam.views.notification;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.poo.Noti;
import com.saaweel.instadam.poo.Post;
import com.saaweel.instadam.poo.User;
import com.squareup.picasso.Picasso;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class Notification extends RecyclerView.ViewHolder {

    private final CircleImageView avatar;

    private final TextView username;

    private final TextView verified;

    private final TextView notifyContent;

    private final TextView notifyAgo;

    private final ImageView postImage;

    public Notification(View view) {
        super(view);

        avatar = view.findViewById(R.id.avatar);
        username = view.findViewById(R.id.username);
        verified = view.findViewById(R.id.verified);
        notifyContent = view.findViewById(R.id.notifyContent);
        notifyAgo = view.findViewById(R.id.notifyAgo);
        postImage = view.findViewById(R.id.postImage);
    }

    public void setNotificationData(Noti noti) {
        User user = noti.getUser();

        Picasso.get().load(user.getAvatar()).into(avatar);
        username.setText(user.getUsername());

        if (user.isVerified())
            verified.setVisibility(View.VISIBLE);

        notifyContent.setText(noti.getContent());

        notifyAgo.setText(this.getTextAgoText(noti.getDate()));

        Post post = noti.getPost();

        if (post != null)
            Picasso.get().load(post.getImage()).into(postImage);
        else
            postImage.setVisibility(View.GONE);
    }

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
