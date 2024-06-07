package com.badalcode.blog.blogapi.controllers;

import com.badalcode.blog.blogapi.payload.ApiResponse;
import com.badalcode.blog.blogapi.payload.UserDto;
import com.badalcode.blog.blogapi.services.userService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private userService userservice;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto = this.userservice.createUSer(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    //put- update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid){
        UserDto updateUser = this.userservice.updateUser(userDto,uid);
        return ResponseEntity.ok(updateUser);
    }

    //ADMIN
    //DELETE-delete user
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
        this.deleteUser(uid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Successfully deleted message",true), HttpStatus.OK);
    }

    //Get= user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getAllUser(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userservice.getUserById(userId));
    }
}
