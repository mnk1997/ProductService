package com.assignment.userservice.dto;

import com.assignment.userservice.models.Session;
import lombok.Data;

@Data
public class TokenValidateResponseDto {
    private String token;
    public static TokenValidateResponseDto fromToken(Session session) {
        TokenValidateResponseDto dto = new TokenValidateResponseDto();
        dto.setToken(session.getToken());
        return dto;
    }
}
