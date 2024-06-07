package com.badalcode.blog.blogapi.repositories;

import com.badalcode.blog.blogapi.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
