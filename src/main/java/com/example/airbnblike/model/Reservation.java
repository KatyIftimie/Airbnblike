package com.example.airbnblike.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name = "reservations")
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private Rental rental;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Review> review;
}
