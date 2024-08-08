package com.santho.chatapp.repos;

import com.santho.chatapp.models.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepo extends MongoRepository<ChatRoom,String > {
    Optional<ChatRoom> findBySenderAndReceiver(String sender, String receiver);
}
