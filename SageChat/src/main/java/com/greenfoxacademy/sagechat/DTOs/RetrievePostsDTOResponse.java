package com.greenfoxacademy.sagechat.DTOs;

import com.greenfoxacademy.sagechat.Models.Post;

import java.util.List;

public class RetrievePostsDTOResponse {

    private String name;

    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
