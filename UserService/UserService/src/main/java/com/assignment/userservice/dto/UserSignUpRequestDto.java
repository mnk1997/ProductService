package com.assignment.userservice.dto;

import com.assignment.userservice.models.Role;
import com.assignment.userservice.models.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserSignUpRequestDto {

    private String email;
    private String password;
    private String phoneNumber;
    private List<String> roles;
    private String firstName;


    public static User toUser(UserSignUpRequestDto signInDto)
    {
        User user = new User();
        user.setEmail(signInDto.getEmail());
        user.setPassword(signInDto.getPassword());
        user.setFirstName(signInDto.getFirstName());
        user.setPhoneNumber(signInDto.getPhoneNumber());
        List<Role> roles = new ArrayList<>();
        if(signInDto.getRoles() != null) {
            for (String role : signInDto.getRoles()) {
                Role r = new Role();
                r.setValue(role);
                roles.add(r);
            }
            user.setRoles(roles);
        }
        return user;
    }
}
