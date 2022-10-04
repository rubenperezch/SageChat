package com.greenfoxacademy.sagechat.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String username;

    private String password;

    private String apiKey;

    @OneToMany(mappedBy = "user")
    private List<Post> postList;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.username = login;
    }

    public User(Long id, String login, String username, String password, List<Post> postList) {
        this.id = id;
        this.login = login;
        this.username = username;
        this.password = password;
        this.postList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
