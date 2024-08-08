package com.santho.chatapp.services;

import com.santho.chatapp.models.ChatMessage;
import com.santho.chatapp.repos.ChatMessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepo repository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage message){
        String chatId = chatRoomService.getRoomId(
                message.getSender(), message.getReceiver(), true
        ).orElseThrow(() -> new NullPointerException("Room Not Exits::"));
        message.setChatId(chatId);
        repository.save(message);
        return message;
    }

    public List<ChatMessage> findMessages(String sender, String receiver){
        Optional<String> chatId = chatRoomService.getRoomId(sender, receiver, false);
        return chatId.map(repository::findAllByChatId).orElse(new ArrayList<>());
    }
}
