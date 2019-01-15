package com.codecool.jpaseriesminiproject;

import com.codecool.jpaseriesminiproject.entities.Episode;
import com.codecool.jpaseriesminiproject.entities.Genre;
import com.codecool.jpaseriesminiproject.entities.Season;
import com.codecool.jpaseriesminiproject.entities.Series;
import com.codecool.jpaseriesminiproject.repositories.EpisodeRepository;
import com.codecool.jpaseriesminiproject.repositories.SeasonRepository;
import com.codecool.jpaseriesminiproject.repositories.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@SpringBootApplication
public class JpaSeriesMiniProjectApplication {

    private SeriesRepository seriesRepository;
    private SeasonRepository seasonRepository;
    private EpisodeRepository episodeRepository;

    @Autowired
    public JpaSeriesMiniProjectApplication(SeriesRepository seriesRepository,
                                           SeasonRepository seasonRepository,
                                           EpisodeRepository episodeRepository) {
        this.seriesRepository = seriesRepository;
        this.seasonRepository = seasonRepository;
        this.episodeRepository = episodeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaSeriesMiniProjectApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {

        return args -> {
            episodeRepository.save(Episode.builder().title("sample ep. name").build());


            Season sampleSeason = Season.builder().seasonNumber(1).build();
            Season sampleSeason1 = Season.builder().seasonNumber(1).build();
            Season sampleSeason2 = Season.builder().seasonNumber(1).build();
            seasonRepository.save(sampleSeason);


            Series sampleSerie = Series.builder()
                    .reviews(Arrays.asList("arrsd","sarrd2"))
                    .title("Game of thrones")
                    .build();
            seriesRepository.save(sampleSerie);
        };
    }

}

