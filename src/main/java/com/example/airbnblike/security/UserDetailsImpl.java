package com.example.airbnblike.security;

import com.example.airbnblike.auth.model.User;
import com.example.airbnblike.auth.model.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.Collection;



public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;


    private String email;

    @JsonIgnore
    private String password;

    private String type;

    public String getType() {
        return type;
    }

    public UserDetailsImpl(String email, String password,
                           String type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }
    public static UserDetailsImpl build(User user) {

        return new UserDetailsImpl(

                user.getEmail(),
                user.getPassword(),
                user.getType().getName().toString());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
