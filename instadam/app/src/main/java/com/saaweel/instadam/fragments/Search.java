package com.saaweel.instadam.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.models.Post;
import com.saaweel.instadam.views.mini_post.MiniPostAdapter;

import java.util.ArrayList;

public class Search extends Fragment {
    private ArrayList<Post> posts;

    public Search() {

    }
    
    public Search(ArrayList<Post> posts) {
        this.posts = posts;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.search_posts);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        recyclerView.setAdapter(new MiniPostAdapter(this.posts));

        return view;
    }
}