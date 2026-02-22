package com.example.springapp.Alumni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.springapp.Alumni.model.Notification;
import com.example.springapp.Alumni.repository.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    // Create Notification
    public Notification createNotification(
            Long userId,
            Long triggeredBy,
            Long referenceId,
            String type,
            String message) {

        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setTriggeredBy(triggeredBy);
        notification.setReferenceId(referenceId);
        notification.setType(type);
        notification.setMessage(message);

        return repository.save(notification);
    }

    // Get User Notifications
    public List<Notification> getUserNotifications(Long userId) {
        return repository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    // Mark as Read
    public String markAsRead(Long notificationId) {

        Notification notification = repository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setRead(true);
        repository.save(notification);

        return "Notification marked as read";
    }

    // Unread Count
    public long getUnreadCount(Long userId) {
        return repository.countByUserIdAndIsReadFalse(userId);
    }
}