package com.example.airbnblike.image.model;

import com.example.airbnblike.rental.model.Rental;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name = "images")
public class Image {

    @Id @GeneratedValue(generator = "imageGen") @TableGenerator(name = "imageGen") private Long id;
    @NotNull private String fileName;
    @NotNull private String originalFileName;
    @NotNull private boolean isMainRentalImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private Rental rental;
}
