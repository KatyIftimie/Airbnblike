package com.example.airbnblike.model;

import com.example.airbnblike.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.persistence.*;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    @NotNull @Email @Column(unique = true) private String email;
    @NotNull private String firstName;
    @NotNull private String lastName;
    @NotNull private String password;
    private boolean isVerified = false;
    @Enumerated(EnumType.STRING) private UserType type;
}
