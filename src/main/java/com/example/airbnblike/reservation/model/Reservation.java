package com.example.airbnblike.reservation.model;

import com.example.airbnblike.auth.model.User;
import com.example.airbnblike.rental.model.Rental;
import com.example.airbnblike.room.model.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity @Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(generator = "reservationGen")
    @TableGenerator(name = "reservationGen")
    private Long id;
    @NotNull private Date checkInDate;
    @NotNull private Date checkOutDate;
    @NotNull private Float totalAmount;
    @Lob @Type(type = "org.hibernate.type.TextType") private String messageToHost;

   @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private Rental rental;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private List<Room> reservedRooms = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_status_id", referencedColumnName = "id")
    private ReservationStatus status;

   @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User guestUser;

    public void addRoom(Room room){
        reservedRooms.add(room);
    }
}


