package com.example.airbnblike.auth.repository;

import com.example.airbnblike.auth.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional <AppUser> getByEmail(String email);
}
