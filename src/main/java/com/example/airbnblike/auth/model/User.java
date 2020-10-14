package com.example.airbnblike.auth.model;

import com.example.airbnblike.rental.model.Rental;
import com.example.airbnblike.reservation.model.Reservation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "userGen")
    @TableGenerator(name = "userGen")
    private Long id;
    @NotNull @Email @Column(unique = true) private String email;
    @NotNull private String phoneNumber;
    @NotNull private String firstName;
    @NotNull private String lastName;
    @JsonIgnore @NotNull private String password;
    private boolean isVerified = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_type", referencedColumnName = "id")
    private UserType type;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    @JsonBackReference
    private List<Rental> rentals = new ArrayList<>();

    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private List<Reservation> reservations = new ArrayList<>();

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }
}
