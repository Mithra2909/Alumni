package com.example.springapp.Alumni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.springapp.Alumni.model.*;
import com.example.springapp.Alumni.repository.*;

@Service
public class ChatService {

    @Autowired
    private ChatRoomRepository chatRoomRepo;

    @Autowired
    private MessageRepository messageRepo;

    // Create or Get Chat Room
    public ChatRoom getOrCreateChatRoom(Long user1, Long user2) {

        Optional<ChatRoom> room =
                chatRoomRepo.findByUser1IdAndUser2Id(user1, user2);

        if (room.isPresent()) return room.get();

        room = chatRoomRepo.findByUser2IdAndUser1Id(user1, user2);

        if (room.isPresent()) return room.get();

        ChatRoom newRoom = new ChatRoom();
        newRoom.setUser1Id(user1);
        newRoom.setUser2Id(user2);

        return chatRoomRepo.save(newRoom);
    }

    // Save Message
    public Message saveMessage(Message message) {
        return messageRepo.save(message);
    }

    // Get Conversation
    public List<Message> getMessages(Long chatRoomId) {
        return messageRepo.findByChatRoomIdOrderBySentAtAsc(chatRoomId);
    }
}