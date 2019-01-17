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
    @Setter(AccessLevel.NONE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ElementCollection
    private List<String> reviews = new ArrayList<>();

    @Transient
    private int numOfUploadedSeasons = 0;

    @OneToMany(mappedBy = "serie", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Season> seasons;

    private String title;

    public void setSeasons(List<Season> seasons) {
        seasons.forEach(season -> season.setSerie(this));
        this.seasons = seasons;
        numOfUploadedSeasons = seasons.size();
    }

    public void addSeason(Season season) {
        if (seasons == null)
            seasons = new ArrayList<>();

        numOfUploadedSeasons++;
        season.setSerie(this);
        seasons.add(season);
    }

    public void addReview(String review) {
        if (reviews == null)
            reviews = new ArrayList<>();
        reviews.add(review);
    }


}
