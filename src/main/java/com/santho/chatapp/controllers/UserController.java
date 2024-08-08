package com.santho.chatapp.controllers;

import com.santho.chatapp.models.User;
import com.santho.chatapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @MessageMapping("/user.add-user")
    @SendTo("/user/topic")
    public User addUser(@Payload User user){
        return userService.connectUser(user);
    }

    @MessageMapping("/user.disconnect-user")
    @SendTo("/user/topic")
    public User disconnect(@Payload User user){
        userService.disconnectUser(user.getEmail());
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.allOnlineUsers());
    }
}
