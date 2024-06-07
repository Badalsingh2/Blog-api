package com.badalcode.blog.blogapi.payload;

import com.badalcode.blog.blogapi.entities.Category;
import com.badalcode.blog.blogapi.entities.Comment;
import com.badalcode.blog.blogapi.entities.Post;
import com.badalcode.blog.blogapi.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private Integer postId;
    private String title;
    private String content;

    //private String imageName="default.png";
    private String ImageName;

    private Date addedDate;

    private categoryDto category;

    private UserDto user;

    private Set<CommentDto> commentSet = new HashSet<>();
}
