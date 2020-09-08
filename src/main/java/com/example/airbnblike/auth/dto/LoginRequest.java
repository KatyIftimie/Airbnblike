package com.example.airbnblike.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email, password;
}
