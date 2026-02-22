package com.example.springapp.Alumni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springapp.Alumni.model.Comment;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);
}