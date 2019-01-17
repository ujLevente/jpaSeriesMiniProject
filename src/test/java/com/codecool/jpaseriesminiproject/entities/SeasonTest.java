package com.codecool.jpaseriesminiproject.entities;

import com.codecool.jpaseriesminiproject.repositories.EpisodeRepository;
import com.codecool.jpaseriesminiproject.repositories.SeasonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class SeasonTest {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    private Season sampleSeason;

    @Before
    public void beforeEachInit() {
        sampleSeason = Season.builder()
                .seasonNumber(1)
                .title("episode title")
                .build();
    }

    @Test
    public void testSaveOneSimple() {
        seasonRepository.save(sampleSeason);
        assertThat(seasonRepository.findAll()).hasSize(1);
    }

    @Test
    public void idGivenAutomatically() {
        seasonRepository.save(sampleSeason);
        seasonRepository.save(new Season());
        seasonRepository.save(new Season());
        assertThat(seasonRepository.findAll()).allMatch(serie -> serie.getId() != null);
    }

    @Test
    public void episodePersistedAndDeletedWithSeason() {
        List<Episode> episodesToAdd = Arrays.asList(new Episode(), new Episode());

        sampleSeason.setEpisodes(episodesToAdd);
        seasonRepository.save(sampleSeason);

        assertThat(episodeRepository.findAll()).hasSize(episodesToAdd.size());

        seasonRepository.deleteAll();

        assertThat(episodeRepository.findAll()).hasSize(0);
    }

    @Test
    public void seasonFieldGetSetInEpisodes() {
        Episode sampleEpisode = new Episode();
        List<Episode> sampleEpisodes = Arrays.asList(new Episode(), new Episode(), new Episode());

        sampleSeason.addEpisode(sampleEpisode);

        assertEquals(sampleSeason, sampleEpisode.getSeason());

        sampleSeason.setEpisodes(sampleEpisodes);

        assertThat(sampleEpisodes).allMatch(season ->
                season.getSeason() != null && season.getSeason().equals(sampleSeason)
        );
    }
}