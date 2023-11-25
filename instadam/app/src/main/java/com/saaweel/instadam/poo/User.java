package com.saaweel.instadam.poo;

public class User {
    private String username;

    private String avatar;

    private boolean verified;

    private int followers;

    private int follows;

    public User(String username) {
        this.username = username;
        this.avatar = "https://i.imgur.com/8UX9lcq.jpg";
        this.verified = false;
        this.followers = 0;
        this.follows = 0;
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

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollows() {
        return follows;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }

    public boolean equals(User user) {
        return this.username.equals(user.getUsername());
    }
}
