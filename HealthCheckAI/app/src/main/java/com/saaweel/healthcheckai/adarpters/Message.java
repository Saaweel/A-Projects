package com.saaweel.healthcheckai.adarpters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.healthcheckai.R;
import com.squareup.picasso.Picasso;

public class Message extends RecyclerView.ViewHolder {
    ImageView avatar;
    TextView username;
    TextView message;
    public Message(View view) {
        super(view);

        avatar = view.findViewById(R.id.avatar);
        username = view.findViewById(R.id.username);
        message = view.findViewById(R.id.message);
    }

    public void setData(String avatar, String username, String message) {
        if (!avatar.isEmpty()) {
            Picasso.get().load(avatar).into(this.avatar);
        }

        this.username.setText(username);
        this.message.setText(message);
    }
}
