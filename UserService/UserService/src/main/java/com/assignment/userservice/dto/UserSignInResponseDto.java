package com.assignment.userservice.dto;

import com.assignment.userservice.models.Session;
import com.assignment.userservice.models.User;
import lombok.Data;

import java.util.List;

@Data
public class UserSignInResponseDto {

    private String token;
   private String username;
    public static UserSignInResponseDto fromSession(Session user) {
        UserSignInResponseDto dto = new UserSignInResponseDto();
        dto.setToken(user.getToken());
        dto.setUsername(user.getUser().getUserName());
        return dto;
    }



}
