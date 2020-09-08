package com.example.airbnblike.model;

import com.example.airbnblike.rental.model.Rental;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name = "addresses")
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    @NotNull private String addressLine1;
    private String addressLine2;
    @NotNull private String city;
    @NotNull private String state;
    @NotNull private String country;
    @NotNull private String zipCode;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    @JsonBackReference
    private Rental rental;
}
