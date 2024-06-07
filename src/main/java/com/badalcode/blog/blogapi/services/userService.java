package com.badalcode.blog.blogapi.services;

import com.badalcode.blog.blogapi.entities.User;
import com.badalcode.blog.blogapi.payload.UserDto;

import java.util.List;

public interface userService {

    UserDto registerNewUser(UserDto user);
    //adduser
    UserDto createUSer(UserDto user);
    UserDto updateUser(UserDto user,Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUser();
    void deleteUser(Integer userId);
}
