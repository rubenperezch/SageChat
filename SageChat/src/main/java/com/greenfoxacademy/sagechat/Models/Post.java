package com.greenfoxacademy.sagechat.Models;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    public Post() {
    }

    public Post(String content, User user) {
        this.content = content;
        this.user = user;
    }

    public Post(String content, User user, Channel channel) {
        this.content = content;
        this.user = user;
        this.channel = channel;
    }

    public Post(Long id, String content, User user) {
        this.id = id;
        this.content = content;
        this.user = user;
    }

    public Post(Long id, String content, User user, Channel channel) {
        this.id = id;
        this.content = content;
        this.user = user;
        this.channel = channel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
