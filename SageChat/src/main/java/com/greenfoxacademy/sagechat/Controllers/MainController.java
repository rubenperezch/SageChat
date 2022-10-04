package com.greenfoxacademy.sagechat.Controllers;

import com.greenfoxacademy.sagechat.Models.Post;
import com.greenfoxacademy.sagechat.Services.ChannelService;
import com.greenfoxacademy.sagechat.Services.PostService;
import com.greenfoxacademy.sagechat.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    UserService userService;

    PostService postService;

    ChannelService channelService;

    @Autowired
    public MainController(UserService userService, PostService postService, ChannelService channelService) {
        this.userService = userService;
        this.postService = postService;
        this.channelService = channelService;
    }

    @GetMapping("/")
    public String renderHome(Model model) {
        model.addAttribute("message","");
        return "index";
    }

    @PostMapping("/")
    public String registerUser(Model model, @RequestParam String login, String password) {
        if(userService.existsUserByLogin(login)) {
            model.addAttribute("message","Login already exists, please pick another");
        } else {
            model.addAttribute("message","User successfully created");
            userService.registerUser(login, password);
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String renderLoginUser(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String returnLoginUser(Model model, @RequestParam String login, String password) {

        Boolean check = userService.loginCheck(login,password);
        if (check) {
            return "redirect:/user/"+userService.findFirstByLogin(login).getId();
       } else {
            model.addAttribute("message", "Login or password incorrect, try again");
            return "login";
        }
    }

    @GetMapping("/user/{id}")
    public String userPage(Model model, @PathVariable Long id, @RequestParam (required = false, defaultValue = "General") String channel) {
        model.addAttribute("username",userService.findFirstById(id));
        model.addAttribute("chats",channelService.findAllPostsByChannel(channel));
        model.addAttribute("channels",channelService.listChannels());
        return "user";
    }

    @PostMapping("/user/{id}")
    public String userPageChannel(Model model, @PathVariable Long id, @RequestParam (required = false, defaultValue = "General") String channel) {
        model.addAttribute("username",userService.findFirstById(id));
        model.addAttribute("chats",channelService.findAllPostsByChannel(channel));
        model.addAttribute("channels",channelService.listChannels());
        return "user";
    }

    @PostMapping("/post/{id}")
    public String postMessage(Model model, @PathVariable Long id, @RequestParam String post, @RequestParam String channel) {
        postService.postMessage(id, post, channel);
        return "redirect:/user/" + id;
    }
}
