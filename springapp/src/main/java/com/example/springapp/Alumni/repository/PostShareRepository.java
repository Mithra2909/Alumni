package com.example.springapp.Alumni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springapp.Alumni.model.PostShare;

public interface PostShareRepository extends JpaRepository<PostShare, Long> {

    long countByPostId(Long postId);
}