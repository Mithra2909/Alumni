package com.example.springapp.Alumni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.springapp.Alumni.model.*;
import com.example.springapp.Alumni.service.InteractionService;

@RestController
@RequestMapping("/api/interactions")
@CrossOrigin("*")
public class InteractionController {

    @Autowired
    private InteractionService service;

    // LIKE
    @PostMapping("/like")
    public String like(@RequestParam Long postId,
                       @RequestParam Long userId) {
        return service.likePost(postId, userId);
    }

    @GetMapping("/likes/{postId}")
    public long likeCount(@PathVariable Long postId) {
        return service.getLikeCount(postId);
    }

    // COMMENT
    @PostMapping("/comment")
    public Comment comment(@RequestBody Comment comment) {
        return service.addComment(comment);
    }

    @DeleteMapping("/comment/{id}")
    public String deleteComment(@PathVariable Long id) {
        service.deleteComment(id);
        return "Comment deleted";
    }

    @GetMapping("/comments/{postId}")
    public List<Comment> getComments(@PathVariable Long postId) {
        return service.getComments(postId);
    }

    // SHARE
    @PostMapping("/share")
    public PostShare share(@RequestParam Long postId,
                           @RequestParam Long userId) {
        return service.sharePost(postId, userId);
    }

    @GetMapping("/shares/{postId}")
    public long shareCount(@PathVariable Long postId) {
        return service.getShareCount(postId);
    }
}