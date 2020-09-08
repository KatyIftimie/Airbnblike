package com.example.airbnblike.rental.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "rental_types")
@TableGenerator(name = "gen", initialValue = 1, allocationSize = 200)
public class RentalType {

    public enum Type {
        HOTEL, HOSTEL, VILLA, GUEST_HOUSE, STUDIO
    }

    @Id @GeneratedValue(strategy = GenerationType.TABLE, generator = "gen") private Long id;
    @NotNull @Enumerated(EnumType.STRING) private Type name;
    private String description;
}
