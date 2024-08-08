package com.santho.chatapp.repos;

import com.santho.chatapp.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
    List<User> findAllByStatus();
}
