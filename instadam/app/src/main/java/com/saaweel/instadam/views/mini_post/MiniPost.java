package com.saaweel.instadam.views.mini_post;

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

public class MiniPost extends RecyclerView.ViewHolder {
    private final ImageView image;

    public MiniPost(View view) {
        super(view);

        image = view.findViewById(R.id.image_mini_post);

        FrameLayout layout = view.findViewById(R.id.layout_mini_post);

        ViewGroup.LayoutParams layoutParams = layout.getLayoutParams();

        DisplayMetrics metrics = new DisplayMetrics();

        ((Activity) view.getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        layoutParams.width = metrics.widthPixels / 3;
        layoutParams.height = metrics.widthPixels / 3;

        layout.setLayoutParams(layoutParams);

    }

    public void setPostData(Post post) {
        Picasso.get().load(post.getImage()).into(image);
    }
}
