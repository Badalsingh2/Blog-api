package com.badalcode.blog.blogapi.services;

import com.badalcode.blog.blogapi.entities.Post;
import com.badalcode.blog.blogapi.payload.PostDto;
import com.badalcode.blog.blogapi.payload.PostResponse;

import java.util.List;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //update
    PostDto updatePost(PostDto postDto,Integer postId);

    //delete
    void deletePost(Integer postId);

    //get all postPo
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    //get single post
    PostDto getPostById(Integer postId);

    //get all post by category
    List<PostDto> getPostByCategory(Integer categoryId);

    //get all post by user
    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPosts(String keyWord);
}
