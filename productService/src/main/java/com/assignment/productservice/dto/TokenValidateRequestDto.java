package com.assignment.productservice.dto;


import lombok.Data;

@Data
public class TokenValidateRequestDto {
    private String token;
    private String userName;

    public static TokenValidateRequestDto from(String  token,String userName) {
        TokenValidateRequestDto tokenValidateRequestDto = new TokenValidateRequestDto();
        tokenValidateRequestDto.token = token;
        tokenValidateRequestDto.userName = userName;
        return tokenValidateRequestDto;
    }
}
