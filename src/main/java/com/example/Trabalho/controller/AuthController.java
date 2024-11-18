package com.example.Trabalho.controller;

import com.example.Trabalho.dto.TokenDTO;
import com.example.Trabalho.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/token")
    public ResponseEntity<TokenDTO> generateToken(
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret
    ) {
        try {
            TokenDTO token = authService.generateToken(clientId, clientSecret);
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).build();
        }
    }
}