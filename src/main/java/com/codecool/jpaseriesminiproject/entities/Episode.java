package com.codecool.jpaseriesminiproject.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Episode {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    private int episodeNumber;

    private String title;
}
