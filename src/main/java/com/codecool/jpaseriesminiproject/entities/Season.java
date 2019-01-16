package com.codecool.jpaseriesminiproject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Season {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    private int seasonNumber;

    private String title;

    @ManyToOne
    private Series serie;

    @OneToMany
    private List<Episode> episodes;

}
