package com.assignment.userservice.dto;

import com.assignment.userservice.models.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserSignUpResponseDto {
    private Date createdAt;
    private String userName;

    public static UserSignUpResponseDto fromUser(User user)
    {
        UserSignUpResponseDto dto = new UserSignUpResponseDto();
        dto.setUserName(user.getUserName());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}
