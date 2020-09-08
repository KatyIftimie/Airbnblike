package com.example.airbnblike.amenity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name = "amenity_types")
public class AmenityType {

    public enum Type {
        RENTAL_AMENITY, ROOM_AMENITY
    }

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    @NotNull @Enumerated(EnumType.STRING) private Type name;
}
