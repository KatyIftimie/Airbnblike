package com.example.airbnblike.bed.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity @Table(name = "beds")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Bed {

    public enum Type {
        SINGLE, DOUBLE, KING, QUEEN, TWIN
    }

    @Id
    @GeneratedValue(generator = "bedGen")
    @TableGenerator(name = "bedGen")
    private Long id;
    @NotNull @Enumerated(EnumType.STRING) private Type type;
    @NotNull private int numberOfPersons;
}
