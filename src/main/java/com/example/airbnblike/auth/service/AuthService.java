package com.example.airbnblike.auth.service;

import com.example.airbnblike.auth.dto.LoginRequest;
import com.example.airbnblike.auth.dto.RegisterRequest;
import com.example.airbnblike.auth.model.User;
import com.example.airbnblike.auth.model.UserType;
import com.example.airbnblike.auth.repository.UserRepository;
import com.example.airbnblike.auth.repository.UserTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public ResponseEntity<String> register(RegisterRequest request){
        ResponseEntity<String> validation = validateRegister(request);
        if (validation.getStatusCode().equals(HttpStatus.OK)) {
            User newUser = createUserFromRequest(request);
            userRepository.save(newUser);
        }
        return validation;
    }

    public ResponseEntity<String> login(LoginRequest request, HttpSession session) {
        ResponseEntity<String> validation = validateLogin(request);
        if (validation.getStatusCode().equals(HttpStatus.OK)) {
            User user = getUserByEmail(request.getEmail());
            session.setAttribute("user", user);
        }
        return validation;
    }

    private User createUserFromRequest(RegisterRequest request) {
        User user = new User();
        UserType userType = userTypeRepository.getOne(Long.valueOf(request.getUserTypeID()));
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setType(userType);
        return user;
    }

    private ResponseEntity<String> validateRegister(RegisterRequest request) {
        User userOptional = getUserByEmail(request.getEmail());
        if (userOptional != null) {
            return new ResponseEntity<>("Email already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return new ResponseEntity<>("Passwords don't match!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Registration successful", HttpStatus.OK);
    }

    private ResponseEntity<String> validateLogin(LoginRequest request) {
        String errorMessage = "Invalid credentials";
        User userOptional = getUserByEmail(request.getEmail());
        if (userOptional == null) {
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!passwordEncoder.matches(request.getPassword(), userOptional.getPassword())) {
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }
}
