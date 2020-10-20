package com.example.airbnblike.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name = "user_types")
public class UserType {

    public enum Type {
        GUEST, HOST, ADMIN
    }

    @Id
    @GeneratedValue(generator = "userTypeGen")
    @TableGenerator(name = "userTypeGen")
    private Long id;
    @NotNull @Enumerated(EnumType.STRING) private Type name;
    private String description;

    @Override
    public String toString() {
        return ""+ name;
    }
}
