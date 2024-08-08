package com.santho.chatapp.services;

import com.santho.chatapp.models.Status;
import com.santho.chatapp.models.User;
import com.santho.chatapp.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;

    public User connectUser(User user){
        user.setStatus(Status.ONLINE);
        return userRepo.save(user);
    }

    public void disconnectUser(String email){
        User saved = userRepo.findById(email)
                .orElse(null);
        if(saved != null){
            saved.setStatus(Status.OFFLINE);
            userRepo.save(saved);
        }
    }

    public List<User> allOnlineUsers(){
        return userRepo.findAllByStatus();
    }
}
