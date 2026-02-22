package com.example.springapp.Alumni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.example.springapp.Alumni.model.Message;
import com.example.springapp.Alumni.service.ChatService;

@Controller
public class WebSocketChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatService chatService;

    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload Message message) {

        Message saved = chatService.saveMessage(message);

        messagingTemplate.convertAndSend(
                "/topic/chat/" + message.getChatRoomId(),
                saved
        );
    }
}