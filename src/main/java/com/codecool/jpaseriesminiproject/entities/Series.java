package com.codecool.jpaseriesminiproject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Series {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Singular
    @ElementCollection
    private List<Genre> genres;

    @Transient
    @Setter(AccessLevel.NONE)
    private int numOfUploadedSeasons;

    @OneToMany
    @Setter(AccessLevel.NONE)
    private List<Season> seasons;

    private String title;

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
        numOfUploadedSeasons = seasons.size();
    }

    public void addSeason(Season season) {
        if (seasons == null)
            seasons = new ArrayList<>();

        numOfUploadedSeasons++;
        seasons.add(season);
    }

}
