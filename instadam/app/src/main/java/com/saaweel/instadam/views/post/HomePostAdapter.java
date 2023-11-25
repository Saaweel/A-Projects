package com.saaweel.instadam.views.post;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.models.Post;

import java.util.ArrayList;

public class HomePostAdapter extends RecyclerView.Adapter<HomePost> {

    private final ArrayList<Post> localDataSet;

    public HomePostAdapter(ArrayList<Post> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public HomePost onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_post, viewGroup, false);

        return new HomePost(view);
    }

    @Override
    public void onBindViewHolder(HomePost post, final int position) {
        post.setPostData(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}