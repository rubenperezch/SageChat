package com.greenfoxacademy.sagechat.Controllers;

import com.greenfoxacademy.sagechat.DTOs.PostMessageDTORequest;
import com.greenfoxacademy.sagechat.DTOs.RegisterUserDTORequest;
import com.greenfoxacademy.sagechat.DTOs.RetrievePostsDTORequest;
import com.greenfoxacademy.sagechat.Services.PostService;
import com.greenfoxacademy.sagechat.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@SpringBootApplication
@RestController
@RequestMapping("API/USER")
public class ApiUserController {


    //WebClient webClient;

    //@PostConstruct
    //public void init() {
    //    webClient = WebClient.builder().baseUrl("https://gfa-chat-broker.herokuapp.com/").defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    //}

    PostService postService;

    UserService userService;

    @Autowired
    public ApiUserController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/register")
    ResponseEntity registerUserApi(@RequestBody (required = false) RegisterUserDTORequest registerUserDTORequest) {
        return userService.registerUserApi(registerUserDTORequest);
    }

    @PostMapping("/login")
    ResponseEntity loginUserApi(@RequestBody (required = false) RegisterUserDTORequest registerUserDTORequest) {
        return userService.loginCheckApi(registerUserDTORequest);
    }

    @PostMapping("/post")
    ResponseEntity postApi(@RequestBody (required = false)PostMessageDTORequest postMessageDTORequest) {
        return postService.postMessageAPI(postMessageDTORequest);
    }

    @PostMapping("/history")
    ResponseEntity retrieveAPI(@RequestBody (required = false) RetrievePostsDTORequest retrievePostsDTORequest) {
        return postService.retrieveAPI(retrievePostsDTORequest);
    }
}
