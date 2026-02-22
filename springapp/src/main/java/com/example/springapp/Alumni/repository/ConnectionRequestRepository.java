package com.example.springapp.Alumni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springapp.Alumni.model.ConnectionRequest;
import java.util.List;
import java.util.Optional;

public interface ConnectionRequestRepository 
        extends JpaRepository<ConnectionRequest, Long> {

    List<ConnectionRequest> findByReceiverIdAndStatus(Long receiverId, String status);

    Optional<ConnectionRequest> 
        findBySenderIdAndReceiverIdAndStatus(Long senderId, Long receiverId, String status);
}