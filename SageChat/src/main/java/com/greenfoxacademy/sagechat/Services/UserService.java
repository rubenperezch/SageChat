package com.greenfoxacademy.sagechat.Services;

import com.greenfoxacademy.sagechat.DTOs.ErrorMessage;
import com.greenfoxacademy.sagechat.DTOs.RegisterUserDTORequest;
import com.greenfoxacademy.sagechat.DTOs.RegisterUserDTOResponse;
import com.greenfoxacademy.sagechat.Models.Post;
import com.greenfoxacademy.sagechat.Models.User;
import com.greenfoxacademy.sagechat.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(String login, String password) {
        if(!userRepository.existsUserByLogin(login) && !userRepository.existsUserByUsername(login)) {
            User user = new User(login,password);
            userRepository.save(user);
        }
    }

    public Boolean existsUserByLogin(String login) {
        return userRepository.existsUserByLogin(login);
    }

    public Boolean loginCheck(String login, String password) {
        if(existsUserByLogin(login) == true && findFirstByLogin(login).getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public User findFirstByLogin(String login) {
        return userRepository.findFirstByLogin(login);
    }

    public User findFirstById(Long id) {
        return userRepository.findFirstById(id);
    }



    //APIS

    public ResponseEntity registerUserApi(RegisterUserDTORequest registerUserDTORequest) {
        if(!userRepository.existsUserByLogin(registerUserDTORequest.getLogin()) && !userRepository.existsUserByUsername(registerUserDTORequest.getLogin())) {
            User user = new User(registerUserDTORequest.getLogin(), registerUserDTORequest.getPassword());
            userRepository.save(user);

            RegisterUserDTOResponse registerUserDTOResponse = new RegisterUserDTOResponse();
            registerUserDTOResponse.setUsername(user.getUsername());
            registerUserDTOResponse.setUserId(user.getId());

            return ResponseEntity.ok(registerUserDTOResponse);
        } else {

            ErrorMessage errorMessage = new ErrorMessage();
            errorMessage.setMessage("Login already exists, pick another one");
            return ResponseEntity.status(404).body(errorMessage);
        }
    }

    public ResponseEntity loginCheckApi(RegisterUserDTORequest registerUserDTORequest) {
        if(existsUserByLogin(registerUserDTORequest.getLogin()) == true && findFirstByLogin(registerUserDTORequest.getLogin()).getPassword().equals(registerUserDTORequest.getPassword())) {
            return ResponseEntity.ok("User logged in");
        } else {
            return ResponseEntity.status(400).build();
        }
    }
}
