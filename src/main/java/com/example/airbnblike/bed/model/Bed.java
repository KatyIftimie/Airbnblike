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
@TableGenerator(name = "gen", initialValue = 1, allocationSize = 200)
public class Bed {

    public enum Type {
        SINGLE, DOUBLE, KING, QUEEN, TWIN
    }

    @Id @GeneratedValue(strategy = GenerationType.TABLE, generator = "gen") private Long id;
    @NotNull @Enumerated(EnumType.STRING) private Type type;
    @NotNull private int numberOfPersons;
}
