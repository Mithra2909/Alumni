package com.example.springapp.Alumni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.springapp.Alumni.model.*;
import com.example.springapp.Alumni.service.ChatService;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin("*")
public class ChatController {

    @Autowired
    private ChatService service;

    @PostMapping("/room")
    public ChatRoom createRoom(@RequestParam Long user1,
                               @RequestParam Long user2) {
        return service.getOrCreateChatRoom(user1, user2);
    }

    @GetMapping("/messages/{roomId}")
    public List<Message> getMessages(@PathVariable Long roomId) {
        return service.getMessages(roomId);
    }
}