package com.example.springapp.Alumni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.springapp.Alumni.model.*;
import com.example.springapp.Alumni.service.ConnectionService;

@RestController
@RequestMapping("/api/connections")
@CrossOrigin("*")
public class ConnectionController {

    @Autowired
    private ConnectionService service;

    // Send Request
    @PostMapping("/request")
    public ConnectionRequest sendRequest(
            @RequestParam Long senderId,
            @RequestParam Long receiverId) {

        return service.sendRequest(senderId, receiverId);
    }

    // Accept
    @PutMapping("/accept/{requestId}")
    public String accept(@PathVariable Long requestId) {
        return service.acceptRequest(requestId);
    }

    // Reject
    @PutMapping("/reject/{requestId}")
    public String reject(@PathVariable Long requestId) {
        return service.rejectRequest(requestId);
    }

    // Pending
    @GetMapping("/pending/{userId}")
    public List<ConnectionRequest> pending(@PathVariable Long userId) {
        return service.getPendingRequests(userId);
    }

    // Follow
    @PostMapping("/follow")
    public Follower follow(
            @RequestParam Long followerId,
            @RequestParam Long followingId) {

        return service.follow(followerId, followingId);
    }

    // Get Followers
    @GetMapping("/followers/{userId}")
    public List<Follower> followers(@PathVariable Long userId) {
        return service.getFollowers(userId);
    }
}