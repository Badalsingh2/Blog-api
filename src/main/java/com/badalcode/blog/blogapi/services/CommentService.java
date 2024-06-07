package com.badalcode.blog.blogapi.services;

import com.badalcode.blog.blogapi.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Integer postId);
    void deleteComment(Integer commentId);
}
