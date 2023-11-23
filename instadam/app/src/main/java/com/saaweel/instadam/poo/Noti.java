package com.saaweel.instadam.poo;

import java.util.Date;

public class Noti {
    private User user;

    private Post post;

    private String content;

    private Date date;

    public Noti(User user, Post post, String content) {
        this.user = user;
        this.post = post;
        this.content = content;
        this.date = new Date();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
