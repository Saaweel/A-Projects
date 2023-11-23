package com.saaweel.instadam.views.post;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.poo.Post;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<HomePost> {

    private ArrayList<Post> localDataSet;

    public CustomAdapter(ArrayList<Post> dataSet) {
        localDataSet = dataSet;
    }

    @Override
    public HomePost onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post, viewGroup, false);

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