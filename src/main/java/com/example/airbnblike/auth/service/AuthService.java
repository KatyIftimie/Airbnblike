package com.example.airbnblike.auth.service;

import com.example.airbnblike.auth.dto.LoginRequest;
import com.example.airbnblike.auth.dto.RegisterRequest;
import com.example.airbnblike.auth.model.AppUser;
import com.example.airbnblike.auth.model.UserType;
import com.example.airbnblike.auth.repository.UserRepository;
import com.example.airbnblike.auth.repository.UserTypeRepository;
import com.example.airbnblike.security.JwtTokenServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenServices jwtTokenServices;
    private final AuthenticationManager authenticationManager;

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

    public ResponseEntity<?> login(LoginRequest request,  HttpServletResponse response) {
        try {
            String email = request.getEmail();
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, request.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(email, roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("email", email);
            model.put("roles", roles);
            model.put("token", token);
            Cookie cookie=new Cookie("token",token);
            cookie.setMaxAge(606024);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);


            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw e;
        }
    }


    private AppUser createUserFromRequest(RegisterRequest request) {
        AppUser appUser = new AppUser();
        UserType userType = userTypeRepository.getOne(Long.valueOf(request.getUserTypeID()));
        appUser.setEmail(request.getEmail());
        appUser.setPhoneNumber(request.getPhoneNumber());
        appUser.setFirstName(request.getFirstName());
        appUser.setLastName(request.getLastName());
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));
        appUser.setType(userType);
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
