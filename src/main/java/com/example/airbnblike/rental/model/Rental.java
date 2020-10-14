package com.example.airbnblike.rental.model;

import com.example.airbnblike.address.model.Address;
import com.example.airbnblike.amenity.model.Amenity;
import com.example.airbnblike.auth.model.User;
import com.example.airbnblike.reservation.model.Reservation;
import com.example.airbnblike.review.model.Review;
import com.example.airbnblike.room.model.Room;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(generator = "rentalGen")
    @TableGenerator(name = "rentalGen")
    private Long id;
    @NotNull private String name;
     @NotNull @Lob @Type(type = "org.hibernate.type.TextType") private String description;
     @NotNull private Instant checkInTime;
     @NotNull private Instant checkOutTime;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Room> rooms = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Amenity> amenities = new ArrayList<>();
    
    @OneToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Reservation> reservations = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    @OneToOne(mappedBy = "rental")
    @JsonManagedReference
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_type_id", referencedColumnName = "id")
    private RentalType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User hostUser;

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addAmenity(Amenity amenity) {
        amenities.add(amenity);
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public Float getMinimumPricePerNight() {
        List<Room> rooms = getRooms();
        Float minPrice = rooms.get(0).getPrice();
        for (Room room : rooms) {
            if(room.getPrice() < minPrice) {
                minPrice = room.getPrice();
            }
        }
        return minPrice;
    }
}
