package com.greenfoxacademy.sagechat.DTOs;

public class RegisterUserDTOResponse {

    String username;

    Long userId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
