package com.saaweel.healthcheckai.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.saaweel.healthcheckai.R;
import com.saaweel.healthcheckai.activities.MainActivity;
import com.saaweel.healthcheckai.adarpters.MessageAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChatFragment extends Fragment {
    RecyclerView messages;
    String myAvatarUrl = "";
    String myUsername;
    List<String []> messageList;
    FirebaseFirestore db;

    public ChatFragment() {
        this.messageList = new ArrayList<>();

        FirebaseApp.initializeApp(this.getContext());

        this.db = FirebaseFirestore.getInstance();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity activity = (MainActivity) requireActivity();

        activity.setTabs("app1");

        activity.getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                activity.changeFragment(new MainFragment());

                activity.setTabs("home");
            }
        });

        this.myUsername = activity.getSharedPreferences("PREFERENCE", MODE_PRIVATE).getString("LOGIN_USERNAME", "");

        this.db.collection("users").whereEqualTo("username", this.myUsername).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.isSuccessful() && task.getResult().getDocuments().size() > 0) {
                    String avatarUrl = task.getResult().getDocuments().get(0).getString("avatar");

                    if (avatarUrl != null) {
                        this.myAvatarUrl = avatarUrl;
                    }
                }
            }
        });

        this.db.collection("messages").orderBy("timestamp").whereEqualTo("chat", this.myUsername).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (int i = 0; i < task.getResult().getDocuments().size(); i++) {
                    Map<String, Object> message = task.getResult().getDocuments().get(i).getData();

                    if (message != null) {
                        String username = (String) message.get("username");
                        String avatar = (String) message.get("avatar");
                        String messageText = (String) message.get("message");

                        this.messageList.add(new String [] {avatar, username, messageText});
                    }
                }

                messages.getAdapter().notifyDataSetChanged();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        messages = view.findViewById(R.id.messages);

        messages.setAdapter(new MessageAdapter(this.messageList));

        EditText messageInput = view.findViewById(R.id.messageInput);
        ImageView sendMessage = view.findViewById(R.id.sendMessage);

        sendMessage.setOnClickListener(v -> {
            String message = messageInput.getText().toString();
            if (!message.isEmpty()) {
                addMessage("user", message);

                messageInput.setText("");

                addMessage("system", "GPT Response");
            }
        });

        return view;
    }

    private void addMessage(String role, String message) {
        Map<String, Object> messageToInsert = new HashMap<>();

        messageToInsert.put("timestamp", System.currentTimeMillis());

        messageToInsert.put("chat", this.myUsername);

        messageToInsert.put("role", role);
        messageToInsert.put("message", message);

        if (role.equals("user")) {
            this.messageList.add(new String [] {this.myAvatarUrl, this.myUsername, message});
            messageToInsert.put("username", this.myUsername);
            messageToInsert.put("avatar", this.myAvatarUrl);
        } else {
            this.messageList.add(new String [] {"", "HealthCheck AI", message});
            messageToInsert.put("username", "HealthCheck AI");
            messageToInsert.put("avatar", "");
        }

        this.db.collection("messages").add(messageToInsert);

        messages.getAdapter().notifyDataSetChanged();
    }
}