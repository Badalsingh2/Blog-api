package com.badalcode.blog.blogapi.repositories;

import com.badalcode.blog.blogapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
        Optional<User> findByEmail(String email);
}
