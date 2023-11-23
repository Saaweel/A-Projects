package com.saaweel.instadam.views.mini_post;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.poo.Post;

import java.util.ArrayList;

public class MiniPostAdapter extends RecyclerView.Adapter<MiniPost> {

    private ArrayList<Post> localDataSet;

    public MiniPostAdapter(ArrayList<Post> dataSet) {
        localDataSet = dataSet;
    }

    @Override
    public MiniPost onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_mini_post, viewGroup, false);

        return new MiniPost(view);
    }

    @Override
    public void onBindViewHolder(MiniPost post, final int position) {
        post.setPostData(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}