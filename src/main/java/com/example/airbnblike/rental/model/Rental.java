package com.example.airbnblike.rental.model;

import com.example.airbnblike.address.model.Address;
import com.example.airbnblike.amenity.model.Amenity;
import com.example.airbnblike.auth.model.User;
import com.example.airbnblike.reservation.model.Reservation;
import com.example.airbnblike.review.model.Review;
import com.example.airbnblike.room.model.Room;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "rentals")
@TableGenerator(name = "gen", initialValue = 1, allocationSize = 200)
public class Rental {

    @Id @GeneratedValue(strategy = GenerationType.TABLE, generator = "gen") private Long id;
    @NotNull private String name;
    @NotNull @Lob @Type(type = "org.hibernate.type.TextType") private String description;
    @NotNull private Instant checkInTime;
    @NotNull private Instant checkOutTime;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Room> rooms;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Amenity> amenities;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Reservation> reservations;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Review> reviews;

    @OneToOne(mappedBy = "rental")
    @JsonManagedReference
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_type_id", referencedColumnName = "id")
    private RentalType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User hostUser;
}
