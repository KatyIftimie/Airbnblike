package com.example.airbnblike.auth.api;

import com.example.airbnblike.auth.dto.LoginRequest;
import com.example.airbnblike.auth.dto.RegisterRequest;
import com.example.airbnblike.auth.model.AppUser;
import com.example.airbnblike.auth.repository.UserRepository;
import com.example.airbnblike.auth.service.AuthService;
import com.example.airbnblike.security.TokenServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthApi {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final TokenServices tokenServices;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody LoginRequest data, HttpServletResponse response) {
//         authService.login(request, session);
        try {
            String email = data.getEmail();
            String password = data.getPassword();
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = tokenServices.createToken(email, roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("email", email);
            model.put("roles", roles);
            model.put("token", token);

            Cookie cookie=new Cookie("token",token);
            cookie.setMaxAge(60*60*24);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);



            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw e;
        }
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
