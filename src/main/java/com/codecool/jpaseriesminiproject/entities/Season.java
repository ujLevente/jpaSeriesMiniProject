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
public class Season {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    private int seasonNumber;

    private String title;

    @ManyToOne
    private Series serie;

    @OneToMany(mappedBy = "season", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Episode> episodes;

    public void setEpisodes(List<Episode> episodes) {
        episodes.forEach(episode -> episode.setSeason(this));
        this.episodes = episodes;
    }

    public void addEpisode(Episode episode) {
        if (episodes == null)
            episodes = new ArrayList<>();

        episode.setSeason(this);
        episodes.add(episode);
    }

}
