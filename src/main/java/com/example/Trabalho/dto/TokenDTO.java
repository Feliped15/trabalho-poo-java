package com.example.Trabalho.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    private String token;
    private Long expiresIn;

    public TokenDTO(String token, long expirationTime) {
        this.token = token;
        this.expiresIn = expirationTime;
    }
}