package com.badalcode.blog.blogapi.repositories;

import com.badalcode.blog.blogapi.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
}
