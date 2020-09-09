package com.example.airbnblike.review.model;

import com.example.airbnblike.auth.model.User;
import com.example.airbnblike.rental.model.Rental;
import com.example.airbnblike.reservation.model.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(generator = "reviewGen")
    @TableGenerator(name = "reviewGen")
    private Long id;
    @Lob @Type(type = "org.hibernate.type.TextType") private String message;
    @NotNull private Instant posted = Instant.now();
    @NotNull private int value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    @JsonIgnoreProperties
    private Rental rental;
}
