package com.example.springapp.Alumni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.springapp.Alumni.model.*;
import com.example.springapp.Alumni.repository.*;

@Service
public class InteractionService {

    @Autowired
    private PostLikeRepository likeRepo;

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private PostShareRepository shareRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private NotificationService notificationService;

    // =========================
    // ❤️ LIKE POST
    // =========================
    public String likePost(Long postId, Long userId) {

        // Prevent duplicate like
        if (likeRepo.existsByPostIdAndUserId(postId, userId)) {
            return "You already liked this post";
        }

        PostLike like = new PostLike();
        like.setPostId(postId);
        like.setUserId(userId);
        likeRepo.save(like);

        // 🔔 Get Post Owner
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Long postOwnerId = post.getUserId();

        // 🔔 Avoid self-notification
        if (!postOwnerId.equals(userId)) {
            notificationService.createNotification(
                    postOwnerId,
                    userId,
                    postId,
                    "LIKE",
                    "Someone liked your post"
            );
        }

        return "Post liked successfully";
    }

    // Get Like Count
    public long getLikeCount(Long postId) {
        return likeRepo.countByPostId(postId);
    }

    // =========================
    // 💬 ADD COMMENT
    // =========================
    public Comment addComment(Comment comment) {

        Comment savedComment = commentRepo.save(comment);

        // 🔔 Get Post Owner
        Post post = postRepo.findById(comment.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Long postOwnerId = post.getUserId();

        // 🔔 Avoid self-notification
        if (!postOwnerId.equals(comment.getUserId())) {
            notificationService.createNotification(
                    postOwnerId,
                    comment.getUserId(),
                    comment.getPostId(),
                    "COMMENT",
                    "Someone commented on your post"
            );
        }

        return savedComment;
    }

    // Delete Comment
    public void deleteComment(Long commentId) {
        commentRepo.deleteById(commentId);
    }

    // Get All Comments of a Post
    public List<Comment> getComments(Long postId) {
        return commentRepo.findByPostId(postId);
    }

    // =========================
    // 🔁 SHARE POST
    // =========================
    public PostShare sharePost(Long postId, Long userId) {

        PostShare share = new PostShare();
        share.setPostId(postId);
        share.setUserId(userId);

        PostShare savedShare = shareRepo.save(share);

        // 🔔 Get Post Owner
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Long postOwnerId = post.getUserId();

        // 🔔 Avoid self-notification
        if (!postOwnerId.equals(userId)) {
            notificationService.createNotification(
                    postOwnerId,
                    userId,
                    postId,
                    "SHARE",
                    "Someone shared your post"
            );
        }

        return savedShare;
    }

    // Get Share Count
    public long getShareCount(Long postId) {
        return shareRepo.countByPostId(postId);
    }
}