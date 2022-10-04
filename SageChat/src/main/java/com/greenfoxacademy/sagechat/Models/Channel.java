package com.greenfoxacademy.sagechat.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @OneToMany(mappedBy = "channel")
    List<Post> channelPosts;

    public Channel() {
    }

    public Channel(String name) {
        this.name = name;
        this.channelPosts = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Post> getChannelPosts() {
        return channelPosts;
    }

    public void setChannelPosts(List<Post> channelPosts) {
        this.channelPosts = channelPosts;
    }
}
