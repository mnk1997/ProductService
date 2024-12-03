package com.assignment.userservice.dto;


import com.assignment.userservice.models.Role;
import com.assignment.userservice.models.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserSignInRequestDto {
    private String userName;
    private String password;





}
