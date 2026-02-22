package com.example.springapp.Alumni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.springapp.Alumni.model.*;
import com.example.springapp.Alumni.repository.*;

@Service
public class ConnectionService {

    @Autowired
    private ConnectionRequestRepository requestRepo;

    @Autowired
    private ConnectionRepository connectionRepo;

    @Autowired
    private FollowerRepository followerRepo;

    // Send Request
    public ConnectionRequest sendRequest(Long senderId, Long receiverId) {

        ConnectionRequest request = new ConnectionRequest();
        request.setSenderId(senderId);
        request.setReceiverId(receiverId);

        return requestRepo.save(request);
    }

    // Accept Request
    public String acceptRequest(Long requestId) {

        ConnectionRequest request = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus("ACCEPTED");
        requestRepo.save(request);

        Connection connection = new Connection(
                request.getSenderId(),
                request.getReceiverId());

        connectionRepo.save(connection);

        return "Connection established successfully!";
    }

    // Reject Request
    public String rejectRequest(Long requestId) {

        ConnectionRequest request = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus("REJECTED");
        requestRepo.save(request);

        return "Request rejected!";
    }

    // Get Pending Requests
    public List<ConnectionRequest> getPendingRequests(Long userId) {
        return requestRepo.findByReceiverIdAndStatus(userId, "PENDING");
    }

    // Follow User
    public Follower follow(Long followerId, Long followingId) {

        Follower follower = new Follower();
        follower.setFollowerId(followerId);
        follower.setFollowingId(followingId);

        return followerRepo.save(follower);
    }

    // Get Followers
    public List<Follower> getFollowers(Long userId) {
        return followerRepo.findByFollowingId(userId);
    }
}