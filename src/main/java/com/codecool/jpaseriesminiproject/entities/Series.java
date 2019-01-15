package com.codecool.jpaseriesminiproject.entities;

import lombok.*;

import javax.persistence.*;
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
    private Genre genre;

    @Transient
    @Setter(AccessLevel.NONE)
    private int numOfUploadedSeasons;

    @ElementCollection
    private List<Season> seasons;

    private String title;

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
        numOfUploadedSeasons = seasons.size();
    }

    public void addSeason(Season season) {
        numOfUploadedSeasons++;
        seasons.add(season);
    }

}
