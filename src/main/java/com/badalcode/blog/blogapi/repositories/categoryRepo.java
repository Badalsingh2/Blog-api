package com.badalcode.blog.blogapi.repositories;

import com.badalcode.blog.blogapi.entities.Category;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryRepo extends JpaRepository<Category, Integer> {
}
