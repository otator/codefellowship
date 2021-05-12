package com.example.codefellows.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String body;
    private String createdAt;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private ApplicationUser user;

    public Post(String body, ApplicationUser user) {
        this.body = body;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm");
        this.createdAt = sdf.format(new Timestamp(System.currentTimeMillis()).getTime());
        this.user = user;
    }

    public Post(){
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }
}
