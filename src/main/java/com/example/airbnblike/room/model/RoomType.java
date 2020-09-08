package com.example.airbnblike.room.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name = "room_types")
public class RoomType {

    public enum Type {
        SINGLE, DOUBLE, TRIPLE, QUAD, SUITE
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    @NotNull @Enumerated(EnumType.STRING) private Type name;
    private int capacity;
}
