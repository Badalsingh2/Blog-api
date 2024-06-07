package com.badalcode.blog.blogapi.Security;

import com.badalcode.blog.blogapi.entities.User;
import com.badalcode.blog.blogapi.exceptions.NewResourceNotFoundException;
import com.badalcode.blog.blogapi.exceptions.ResourceNotFoundException;
import com.badalcode.blog.blogapi.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //loading user from database by username
        User user=this.userRepo.findByEmail(username).orElseThrow(()->new NewResourceNotFoundException("user","email",username));
        System.out.println(user);

        return user;
    }
}
