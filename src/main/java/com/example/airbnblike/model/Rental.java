package com.example.airbnblike.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "rentals")
public class Rental {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    @NotNull @Lob private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Room> rooms;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Amenity> amenities;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Reservation> reservations;

    @OneToOne(mappedBy = "rental")
    @JsonManagedReference
    private Address address;
}
