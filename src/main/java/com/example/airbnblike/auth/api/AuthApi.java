package com.example.airbnblike.auth.api;

import com.example.airbnblike.auth.dto.LoginRequest;
import com.example.airbnblike.auth.dto.RegisterRequest;
import com.example.airbnblike.auth.model.AppUser;
import com.example.airbnblike.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthApi {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request, HttpSession session) {
        return authService.login(request, session);
    }

    @GetMapping("/is-logged-in")
    public boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    @GetMapping("/logout")
    public void logoutUser(HttpSession session, HttpServletResponse response) throws IOException {
        session.removeAttribute("user");
        response.sendRedirect("/");
    }

    @GetMapping("/get-user/{email}")
    public Optional<AppUser> returnUser(@PathVariable String email ) {
        return authService.getUserByEmail(email);
    }
}
