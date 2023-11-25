package com.saaweel.instadam.views.notification;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.instadam.R;
import com.saaweel.instadam.models.Noti;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<Notification> {

    private final ArrayList<Noti> localDataSet;

    public NotificationAdapter(ArrayList<Noti> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public Notification onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_notification, viewGroup, false);

        return new Notification(view);
    }

    @Override
    public void onBindViewHolder(Notification notification, final int position) {
        notification.setNotificationData(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}