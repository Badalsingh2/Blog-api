package com.badalcode.blog.blogapi.services.impl;

import com.badalcode.blog.blogapi.config.AppConstants;
import com.badalcode.blog.blogapi.entities.Role;
import com.badalcode.blog.blogapi.entities.User;
import com.badalcode.blog.blogapi.exceptions.ResourceNotFoundException;
import com.badalcode.blog.blogapi.payload.UserDto;
import com.badalcode.blog.blogapi.repositories.RoleRepo;
import com.badalcode.blog.blogapi.repositories.UserRepo;
import com.badalcode.blog.blogapi.services.userService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class userServiceImpl implements userService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user=this.modelMapper.map(userDto,User.class);

        //encode the password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        //roles
        Role role=this.roleRepo.findById(AppConstants.NORMAL_USER).get();

        user.getRoles().add(role);

        User newUser = this.userRepo.save(user);
        return this.modelMapper.map(newUser,UserDto.class) ;
    }

    @Override
    public UserDto createUSer(UserDto userDto) {
        User user= this.dtoToUser(userDto);
        User savedUser=this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User"," Id ",userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        
        User updatedUser=this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updatedUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDto user){
        User user1=this.modelMapper.map(user,User.class);
//        user1.setId(user.getId());
//        user1.setName(user.getName());
//        user1.setEmail(user.getEmail());
//        user1.setAbout(user.getAbout());
//        user1.setPassword(user.getPassword());
        return user1;
    }

    private UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user,UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setAbout(user.getAbout());
//        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
