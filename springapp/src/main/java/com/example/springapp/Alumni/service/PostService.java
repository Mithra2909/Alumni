package com.example.springapp.Alumni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import com.example.springapp.Alumni.model.Post;
import com.example.springapp.Alumni.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    // Create Post
    public Post createPost(Post post) {
        return repository.save(post);
    }

    // Edit Post
    public Post updatePost(Long postId, Post updatedPost) {

        Post existing = repository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        existing.setContent(updatedPost.getContent());
        existing.setPostType(updatedPost.getPostType());

        return repository.save(existing);
    }

    // Delete Post
    public void deletePost(Long postId) {
        repository.deleteById(postId);
    }

    // Fetch Feed with Pagination & Sorting
    public Page<Post> getFeed(int page, int size) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending()
        );

        return repository.findAll(pageable);
    }
}