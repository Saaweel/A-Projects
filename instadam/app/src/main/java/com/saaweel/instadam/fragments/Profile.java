package com.saaweel.instadam.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.poo.Post;
import com.saaweel.instadam.poo.User;
import com.saaweel.instadam.views.mini_post.MiniPostAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Profile extends Fragment {
    private User user;

    private ArrayList<Post> posts;

    private boolean isMyProfile;

    public Profile() {

    }

    public Profile(User user, ArrayList<Post> posts) {
        this.user = user;
        this.posts = posts;
    }

    public Profile(User user, ArrayList<Post> posts, boolean isMyProfile) {
        this.user = user;
        this.posts = posts;
        this.isMyProfile = isMyProfile;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.profile_posts);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        recyclerView.setAdapter(new MiniPostAdapter(this.posts));

        TextView profileUsername = view.findViewById(R.id.profileUsername);
        profileUsername.setText(this.user.getUsername());

        ImageView avatar = view.findViewById(R.id.avatar);
        Picasso.get().load(this.user.getAvatar()).into(avatar);

        TextView postCount = view.findViewById(R.id.postCount);
        postCount.setText(this.posts.size() + "");

        TextView followerConut = view.findViewById(R.id.followerConut);
        followerConut.setText(this.user.getFollowers() + "");

        TextView followsConut = view.findViewById(R.id.followsConut);
        followsConut.setText(this.user.getFollows() + "");

        if (isMyProfile) {
            Button followButton = view.findViewById(R.id.followButton);
            followButton.setVisibility(View.INVISIBLE);
        }

        return view;
    }
}