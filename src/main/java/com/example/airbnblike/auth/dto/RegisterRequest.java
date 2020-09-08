package com.example.airbnblike.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Setter @Getter
public class RegisterRequest {
    private String email, firstName, lastName, password, confirmPassword, phoneNumber, userTypeID;
}
