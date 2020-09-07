package com.example.airbnblike.model;

import com.example.airbnblike.enums.AmenityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name = "amenities")
public class Amenity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    @NotNull @Enumerated(EnumType.STRING) private AmenityType type;
}
