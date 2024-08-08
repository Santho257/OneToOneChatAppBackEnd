package com.santho.chatapp.controllers;

import com.santho.chatapp.models.ChatMessage;
import com.santho.chatapp.services.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatMessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void sendMessage(@Payload ChatMessage message){
        ChatMessage saved = messageService.save(message);
        messagingTemplate.convertAndSendToUser(
                message.getReceiver(), "/queue/messages", saved
        );
    }

    @GetMapping("/messages/{sender}/{receiver}")
    public ResponseEntity<List<ChatMessage>> allMessages(@PathVariable String sender, @PathVariable String receiver){
        return ResponseEntity.ok(messageService.findMessages(sender,receiver));
    }
}
