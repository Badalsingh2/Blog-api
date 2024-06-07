package com.badalcode.blog.blogapi.repositories;

import com.badalcode.blog.blogapi.entities.Category;
import com.badalcode.blog.blogapi.entities.Post;
import com.badalcode.blog.blogapi.entities.User;
import com.badalcode.blog.blogapi.payload.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {


    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);
}
