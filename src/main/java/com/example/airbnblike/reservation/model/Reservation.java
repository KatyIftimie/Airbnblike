package com.example.airbnblike.reservation.model;

import com.example.airbnblike.auth.model.User;
import com.example.airbnblike.review.model.Review;
import com.example.airbnblike.rental.model.Rental;
import com.example.airbnblike.room.model.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name = "reservations")
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    @NotNull private Date checkInDate;
    @NotNull private Date checkOutDate;
    @NotNull private Float totalAmount;
    @Lob @Type(type = "org.hibernate.type.TextType") private String messageToHost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private Rental rental;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private List<Room> reservedRooms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_status_id", referencedColumnName = "id")
    private ReservationStatus status;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User guestUser;
}
