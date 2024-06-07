package com.badalcode.blog.blogapi.controllers;

import com.badalcode.blog.blogapi.Security.JwtTokenHelper;
import com.badalcode.blog.blogapi.exceptions.ApiException;
import com.badalcode.blog.blogapi.payload.JwtAuthRequest;
import com.badalcode.blog.blogapi.payload.JwtAuthResponse;
import com.badalcode.blog.blogapi.payload.UserDto;
import com.badalcode.blog.blogapi.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private userService userservice;

     @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
             @RequestBody JwtAuthRequest request
             ) throws Exception {
        this.authenticate(request.getUsername(),request.getPassword());

         UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());
         System.out.println("in AuthController");

         System.out.println(userDetails.getUsername());
         System.out.println(userDetails.getPassword());

        String token = this.jwtTokenHelper.generateToken(userDetails);
         System.out.println(token);
        JwtAuthResponse response = new JwtAuthResponse();

        response.setToken(token);

        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
     }

    private void authenticate(String username, String password) throws Exception {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        try {
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }
        catch(BadCredentialsException e){
            System.out.println("Invalid details");
            throw new ApiException("Invalid username password");
        }
        catch (DisabledException e){
            throw new Exception("User is disabled");
        }

    }
    @PostMapping("/register")
    public  ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
         UserDto registeredUser=this.userservice.registerNewUser(userDto);

         return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
    }
}
