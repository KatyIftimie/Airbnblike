package com.example.airbnblike.room.model;

import com.example.airbnblike.amenity.model.Amenity;
import com.example.airbnblike.bed.model.Bed;
import com.example.airbnblike.rental.model.Rental;
import com.example.airbnblike.reservation.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name = "rooms")
@TableGenerator(name = "gen", initialValue = 1, allocationSize = 200)
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.TABLE, generator = "gen") private Long id;
    @NotNull private String name;
    @Lob private String description;
    @NotNull private Float price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "amenity_id", referencedColumnName = "id")
    private List<Amenity> amenities;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Bed> beds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private Rental rental;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private List<Reservation> reservations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id", referencedColumnName = "id")
    private RoomType type;
}
