package com.santho.chatapp.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class ChatMessage {
    @Id
    private String id;
    private String chatId;
    private String sender;
    private String receiver;
    private String content;
    private LocalDateTime timestamp;
}
