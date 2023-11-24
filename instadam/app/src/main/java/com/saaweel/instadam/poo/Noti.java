package com.saaweel.instadam.poo;

import java.util.Date;

public class Noti {
    private User user;

    private String image;

    private String content;

    private Date date;

    public Noti(User user, String image, String content) {
        this.user = user;
        this.image = image;
        this.content = content;
        this.date = new Date();
    }

    public Noti(User user, String content) {
        this.user = user;
        this.content = content;
        this.date = new Date();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImage() {
        return this.image;
    }

    public void setPost(String image) {
        this.image = image;
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
