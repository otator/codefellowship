package com.example.codefellows.repository;

import com.example.codefellows.model.ApplicationUser;
import com.example.codefellows.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);


}
