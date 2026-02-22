package com.example.springapp.Alumni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.springapp.Alumni.model.Notification;
import com.example.springapp.Alumni.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationController {

    @Autowired
    private NotificationService service;

    // Get Notifications
    @GetMapping("/{userId}")
    public List<Notification> getNotifications(@PathVariable Long userId) {
        return service.getUserNotifications(userId);
    }

    // Mark as Read
    @PutMapping("/read/{notificationId}")
    public String markAsRead(@PathVariable Long notificationId) {
        return service.markAsRead(notificationId);
    }

    // Unread Count
    @GetMapping("/unread/{userId}")
    public long unreadCount(@PathVariable Long userId) {
        return service.getUnreadCount(userId);
    }
}