package com.badalcode.blog.blogapi.payload;

import com.badalcode.blog.blogapi.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min=4,message = "Please enter more than four letters")
    private String name;

    @Email(message = "Enter Proper Email!!!")
    private String email;

    @NotNull
    @Size(min=8,max=8,message = "message should be of eight character")
    
    private String password;

    @NotNull
    private String about;

    private Set<RoleDto> roles=new HashSet<>();
}
