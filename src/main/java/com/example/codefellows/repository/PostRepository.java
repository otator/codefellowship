package com.example.codefellows.repository;

import com.example.codefellows.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUser_id(Long id);

    @Query(value = "select * from post where user_id in (select following_user from followers where followers.user_id = (select application_user.id from application_user where username = ?1));", nativeQuery = true)
    List<Post> findFollowingPosts(String username);
 }
