package com.saaweel.instadam.models;

import java.util.Random;

public class Post {
    private User user;

    private String image;

    private String description;

    private int likes;

    public Post(User user, String image) {
        this.user = user;
        this.image = image;
        this.description = "";
        this.likes = new Random().nextInt(20000);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
