package com.saaweel.instadam.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.models.Noti;
import com.saaweel.instadam.views.notification.NotificationAdapter;

import java.util.ArrayList;

public class Notify extends Fragment {
    ArrayList<Noti> notifications;

    public Notify() {

    }

    public Notify(ArrayList<Noti> notifications) {
        this.notifications = notifications;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notify, container, false);

        NotificationAdapter customAdapter = new NotificationAdapter(this.notifications);
        RecyclerView recyclerView = view.findViewById(R.id.notifications);
        recyclerView.setAdapter(customAdapter);

        return view;
    }
}