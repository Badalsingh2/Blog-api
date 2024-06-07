package com.badalcode.blog.blogapi.controllers;

import com.badalcode.blog.blogapi.entities.Comment;
import com.badalcode.blog.blogapi.payload.ApiResponse;
import com.badalcode.blog.blogapi.payload.CommentDto;
import com.badalcode.blog.blogapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,@PathVariable Integer postId)
    {
        CommentDto createComment=this.commentService.createComment(comment,postId);
        return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComments(@PathVariable Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully",true),HttpStatus.OK);

    }
}
