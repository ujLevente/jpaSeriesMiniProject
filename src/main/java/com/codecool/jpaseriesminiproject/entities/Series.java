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
    private List<String> reviews = new ArrayList<>();

    @Transient
    private int numOfUploadedSeasons = 0;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Setter(AccessLevel.NONE)
    private List<Season> seasons = new ArrayList<>();

    private String title;

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
        numOfUploadedSeasons = seasons.size();
    }

    public void addSeason(Season season) {
        numOfUploadedSeasons++;
        seasons.add(season);
    }

    public void addReview(String review) {
        reviews.add(review);
    }
}
