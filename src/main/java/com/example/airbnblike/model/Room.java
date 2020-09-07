package com.example.airbnblike.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name = "rooms")
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.AUTO) private Long id;
    @NotNull private String name;
    @Enumerated(EnumType.STRING) private String RoomType;
    @NotNull private Float price;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Bed> beds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private Rental rental;
}
