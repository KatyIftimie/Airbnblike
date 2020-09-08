package com.example.airbnblike.auth.repository;

import com.example.airbnblike.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByEmail(String email);
}