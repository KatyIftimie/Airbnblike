package com.example.airbnblike.rental.model;

import com.example.airbnblike.model.Address;
import com.example.airbnblike.model.Amenity;
import com.example.airbnblike.model.Reservation;
import com.example.airbnblike.model.Room;
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
    @NotNull private String name;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_type_id", referencedColumnName = "id")
    private RentalType rentalType;
}
