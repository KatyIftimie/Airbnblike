package com.example.airbnblike.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name = "reservation_statuses")
@TableGenerator(name = "gen", initialValue = 1, allocationSize = 200)
public class ReservationStatus {

    public enum Status {
        BOOKED, COMPLETED, CANCELED, RESCHEDULED
    }

    @Id @GeneratedValue(strategy = GenerationType.TABLE, generator = "gen") private Long id;
    @NotNull @Enumerated(EnumType.STRING) private Status name = Status.BOOKED;
}
