package com.saaweel.healthcheckai.adarpters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saaweel.healthcheckai.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<Message> {
    List<String []> messageDataSet;
    public MessageAdapter(List<String []> messageDataSet) {
        this.messageDataSet = messageDataSet;
    }

    @Override
    public Message onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_message, viewGroup, false);
        return new Message(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Message message, int position) {
        String [] data = messageDataSet.get(position);

        message.setData(data[0], data[1], data[2]);
    }

    @Override
    public int getItemCount() {
        return messageDataSet.size();
    }
}