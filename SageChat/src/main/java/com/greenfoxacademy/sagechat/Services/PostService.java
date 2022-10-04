package com.greenfoxacademy.sagechat.Services;

import com.greenfoxacademy.sagechat.DTOs.ErrorMessage;
import com.greenfoxacademy.sagechat.DTOs.PostMessageDTORequest;
import com.greenfoxacademy.sagechat.DTOs.RetrievePostsDTORequest;
import com.greenfoxacademy.sagechat.DTOs.RetrievePostsDTOResponse;
import com.greenfoxacademy.sagechat.Models.Channel;
import com.greenfoxacademy.sagechat.Models.Post;
import com.greenfoxacademy.sagechat.Models.User;
import com.greenfoxacademy.sagechat.Repositories.ChannelRepository;
import com.greenfoxacademy.sagechat.Repositories.PostRepository;
import com.greenfoxacademy.sagechat.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    PostRepository postRepository;

    UserRepository userRepository;

    ChannelRepository channelRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository, ChannelRepository channelRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.channelRepository = channelRepository;
    }

    public void postMessage(Long id, String post, String channel) {


        Post chat = new Post(post,userRepository.findFirstById(id), channelRepository.findFirstByName(channel));

        postRepository.save(chat);

    }

    //APIS

    public ResponseEntity postMessageAPI(PostMessageDTORequest postMessageDTORequest) {
        if(userRepository.existsUserByLogin(postMessageDTORequest.getLogin()) == true && userRepository.findFirstByLogin(postMessageDTORequest.getLogin()).getPassword().equals(postMessageDTORequest.getPassword()) && channelRepository.existsChannelByName(postMessageDTORequest.getChannelName()) == true) {

            Post chat = new Post(postMessageDTORequest.getContent(), userRepository.findFirstByLogin(postMessageDTORequest.getLogin()),channelRepository.findFirstByName(postMessageDTORequest.getChannelName()));
            postRepository.save(chat);

            ErrorMessage message = new ErrorMessage();
            message.setMessage("Your message " +postMessageDTORequest.getContent()+ " has been posted on "+ postMessageDTORequest.getChannelName()+ " as user "+postMessageDTORequest.getLogin());

            return ResponseEntity.ok(message);
        } else {
            ErrorMessage message = new ErrorMessage();
            message.setMessage("Something went wrong, your login does not exist, or password not match, or channel does not exist. Try again.");

            return ResponseEntity.status(400).body(message);

        }
    }

    public ResponseEntity retrieveAPI(RetrievePostsDTORequest retrievePostsDTORequest) {
        if(userRepository.existsUserByLogin(retrievePostsDTORequest.getLogin()) == true && userRepository.findFirstByLogin(retrievePostsDTORequest.getLogin()).getPassword().equals(retrievePostsDTORequest.getPassword()) && channelRepository.existsChannelByName(retrievePostsDTORequest.getChannelName()) == true) {


            List<Post> chats = new ArrayList<>(channelRepository.findFirstByName(retrievePostsDTORequest.getChannelName()).getChannelPosts());

            List<RetrievePostsDTOResponse> listPost = new ArrayList<>();

            for (Post chat: chats) {

                RetrievePostsDTOResponse retr = new RetrievePostsDTOResponse();
                retr.setContent(chat.getContent());
                retr.setName(chat.getUser().getUsername());

                listPost.add(retr);
            }

            return ResponseEntity.ok(listPost);
        } else {

            ErrorMessage error = new ErrorMessage();
            error.setMessage("Something went wrong, your login does not exist, or password not match, or channel does not exist. Try again.");

            return ResponseEntity.status(400).body(error);
        }
    }
}
