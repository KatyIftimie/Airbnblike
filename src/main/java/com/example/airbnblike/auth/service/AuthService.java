package com.example.airbnblike.auth.service;

import com.example.airbnblike.auth.dto.LoginRequest;
import com.example.airbnblike.auth.dto.RegisterRequest;
import com.example.airbnblike.auth.model.AppUser;
import com.example.airbnblike.auth.model.UserType;
import com.example.airbnblike.auth.repository.UserRepository;
import com.example.airbnblike.auth.repository.UserTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final PasswordEncoder passwordEncoder =PasswordEncoderFactories.createDelegatingPasswordEncoder();



    public Optional<AppUser> getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public AppUser getUserByID(Long ID) {
        return userRepository.getOne(ID);
    }

    public ResponseEntity<String> register(RegisterRequest request){
        ResponseEntity<String> validation = validateRegister(request);
        if (validation.getStatusCode().equals(HttpStatus.OK)) {
            AppUser newAppUser = createUserFromRequest(request);
            userRepository.save(newAppUser);
        }
        return validation;
    }

    public ResponseEntity<String> login(LoginRequest request, HttpSession session) {
        ResponseEntity<String> validation = validateLogin(request);
        if (validation.getStatusCode().equals(HttpStatus.OK)) {
            Optional<AppUser> appUser = getUserByEmail(request.getEmail());
            session.setAttribute("user", appUser);
        }
        return validation;
    }

    private AppUser createUserFromRequest(RegisterRequest request) {
        AppUser appUser = new AppUser();
        UserType userType = userTypeRepository.getOne(Long.valueOf(request.getUserTypeID()));
        appUser.addUserType(userType);
        appUser.setEmail(request.getEmail());
        appUser.setPhoneNumber(request.getPhoneNumber());
        appUser.setFirstName(request.getFirstName());
        appUser.setLastName(request.getLastName());
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));
        return appUser;
    }

    private ResponseEntity<String> validateRegister(RegisterRequest request) {
        Optional<AppUser> appUserOptional = getUserByEmail(request.getEmail());
        if (appUserOptional != null) {
            return new ResponseEntity<>("Email already exists!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            return new ResponseEntity<>("Passwords don't match!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Registration successful", HttpStatus.OK);
    }

    private ResponseEntity<String> validateLogin(LoginRequest request) {
        String errorMessage = "Invalid credentials";
        Optional<AppUser> appUserOptional = getUserByEmail(request.getEmail());
        if (appUserOptional == null) {
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!passwordEncoder.matches(request.getPassword(), appUserOptional.get().getPassword())) {
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }
}
