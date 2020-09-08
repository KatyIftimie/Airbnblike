package com.example.airbnblike.amenity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name = "amenities")
@TableGenerator(name = "gen", initialValue = 1, allocationSize = 200)
public class Amenity {
    @Id @GeneratedValue(strategy = GenerationType.TABLE, generator = "gen") private Long id;
    @NotNull private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amenity_type_id", referencedColumnName = "id")
    private AmenityType amenityType;
}
