package com.example.springapp.Alumni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springapp.Alumni.model.Follower;
import java.util.List;

public interface FollowerRepository 
        extends JpaRepository<Follower, Long> {

    List<Follower> findByFollowingId(Long followingId);
}