package com.assignment.userservice.dto;


import lombok.Data;

@Data
public class TokenValidateRequestDto {
    private String token;
    private String userName;
}
