package com.example.airbnblike.room.model;

import com.example.airbnblike.amenity.model.Amenity;
import com.example.airbnblike.bed.model.Bed;
import com.example.airbnblike.rental.model.Rental;
import com.example.airbnblike.reservation.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(generator = "roomsGen")
    @TableGenerator(name = "roomGen")
    private Long id;
    @NotNull private String name;
    @Lob @Type(type = "org.hibernate.type.TextType") private String description;
    @NotNull private Float price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "amenity_id", referencedColumnName = "id")
    private List<Amenity> amenities = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "bed_id", referencedColumnName = "id")
    private List<Bed> beds = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private Rental rental;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id", referencedColumnName = "id")
    private RoomType type;

    public void addAmenity(Amenity amenity) {
        amenities.add(amenity);
    }

    public void addBed(Bed bed) {
        beds.add(bed);
    }
}
