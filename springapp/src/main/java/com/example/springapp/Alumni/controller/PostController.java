package com.example.springapp.Alumni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import com.example.springapp.Alumni.model.Post;
import com.example.springapp.Alumni.service.PostService;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin("*")
public class PostController {

    @Autowired
    private PostService service;

    // Create Post
    @PostMapping
    public Post create(@RequestBody Post post) {
        return service.createPost(post);
    }

    // Edit Post
    @PutMapping("/{id}")
    public Post update(@PathVariable Long id,
                       @RequestBody Post post) {
        return service.updatePost(id, post);
    }

    // Delete Post
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deletePost(id);
        return "Post deleted successfully";
    }

    // Fetch Feed (Pagination + Sorting)
    @GetMapping
    public Page<Post> getFeed(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return service.getFeed(page, size);
    }
}

