package com.saaweel.instadam.poo;

public class User {
    private String username;

    private String avatar;

    private boolean verified;

    public User(String username) {
        this.username = username;
        this.avatar = "https://i.imgur.com/8UX9lcq.jpg";
        this.verified = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
