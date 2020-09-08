package com.example.airbnblike.rental.model;

import com.example.airbnblike.enums.BedType;
import com.example.airbnblike.model.Reservation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rental_types")
public class RentalType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    @NotNull private String name;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Rental> rentals;
}
