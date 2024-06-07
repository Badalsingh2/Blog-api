package com.badalcode.blog.blogapi.payload;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String username;

    private String password;
}
