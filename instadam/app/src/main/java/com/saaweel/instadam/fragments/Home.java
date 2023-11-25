package com.saaweel.instadam.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.models.Post;
import com.saaweel.instadam.views.post.HomePostAdapter;
import com.saaweel.instadam.R;

import java.util.ArrayList;

public class Home extends Fragment {
    private ArrayList<Post> posts;

    public Home() {

    }

    public Home(ArrayList<Post> posts) {
        this.posts = posts;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        HomePostAdapter customAdapter = new HomePostAdapter(this.posts);
        RecyclerView recyclerView = view.findViewById(R.id.home_posts);
        recyclerView.setAdapter(customAdapter);

        return view;
    }
}