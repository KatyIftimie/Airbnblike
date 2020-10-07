package com.example.airbnblike.security;

import com.example.airbnblike.auth.model.User;
import com.example.airbnblike.auth.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Component
public class CustomUserDetailsService {
    private UserRepository users;
    private UserDetailsImpl userDetails;

    public CustomUserDetailsService(UserRepository users) {
        this.users = users;
    }

    /**
     * Loads the user from the DB and converts it to Spring Security's internal User object.
     * Spring will call this code to retrieve a user upon login from the DB.
     */

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = users.getByEmail(email);
                if (user == null) {
                    throw  new UsernameNotFoundException("Username: " + email + " not found");
                }else{
                    return userDetails.build(user);
                }
    }
}