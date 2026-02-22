package com.example.springapp.Alumni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springapp.Alumni.model.ChatRoom;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    Optional<ChatRoom> findByUser1IdAndUser2Id(Long user1Id, Long user2Id);

    Optional<ChatRoom> findByUser2IdAndUser1Id(Long user1Id, Long user2Id);
}