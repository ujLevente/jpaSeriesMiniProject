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
    private Genre genre;

    @ElementCollection
    private List<String> reviews;

    @Transient
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

    public void addReview(String review) {
        if (reviews == null)
            reviews = new ArrayList<>();
        reviews.add(review);
    }
}
