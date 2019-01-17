package com.codecool.jpaseriesminiproject.entities;

import com.codecool.jpaseriesminiproject.repositories.SeasonRepository;
import com.codecool.jpaseriesminiproject.repositories.SeriesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class SeriesTest {

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    private Series sampleSerie;

    @Before
    public void beforeEachInit() {
        sampleSerie = Series.builder()
                .title("Game of Thrones")
                .genre(Genre.ACTION)
                .build();
    }

    @Test
    public void testSaveOneSimple() {
        seriesRepository.save(sampleSerie);
        assertThat(seriesRepository.findAll()).hasSize(1);
    }

    @Test
    public void idGivenAutomatically() {
        seriesRepository.save(sampleSerie);
        seriesRepository.save(new Series());
        seriesRepository.save(new Series());
        assertThat(seriesRepository.findAll()).allMatch(serie -> serie.getId() != null);
    }

    @Test
    public void addedReviewsSavedWithSerie() {
        List<String> reviewsToAdd = Arrays.asList("review1", "review2", "review3");
        sampleSerie.setReviews(reviewsToAdd);
        seriesRepository.save(sampleSerie);

        List<String> DbSerieReviews = seriesRepository.findAll().get(0).getReviews();

        assertEquals(DbSerieReviews.size(), reviewsToAdd.size());
    }

    @Test
    public void transientFieldNotPresentInDb() {
        seriesRepository.save(sampleSerie);
        assertThat(seriesRepository.findAll()).allMatch(serie -> serie.getNumOfUploadedSeasons() == 0L);
    }

    @Test
    public void numOfUploadedSeasonsIncrementsWithSeasonAdded() {
        int numOfAddedSeasons = 0;

        assertEquals(sampleSerie.getNumOfUploadedSeasons(), numOfAddedSeasons);

        List<Season> seasonsToAdd = new ArrayList<>(Arrays.asList(new Season(), new Season()));
        sampleSerie.setSeasons(seasonsToAdd);
        numOfAddedSeasons = seasonsToAdd.size();

        assertEquals(sampleSerie.getNumOfUploadedSeasons(), numOfAddedSeasons);

        sampleSerie.addSeason(new Season());
        numOfAddedSeasons++;

        assertEquals(sampleSerie.getNumOfUploadedSeasons(), numOfAddedSeasons);
    }

    @Test
    public void seasonPersistedAndDeletedWithSeries() {
        List<Season> seasonsToAdd = Arrays.asList(new Season(), new Season());

        sampleSerie.setSeasons(seasonsToAdd);
        seriesRepository.save(sampleSerie);

        assertThat(seasonRepository.findAll()).hasSize(seasonsToAdd.size());

        seriesRepository.deleteAll();

        assertThat(seasonRepository.findAll()).hasSize(0);
    }

    @Test
    public void serieFieldGetSetInSeason() {
        Season sampleSeason = new Season();
        List<Season> sampleSeasons = Arrays.asList(new Season(), new Season(), new Season());

        sampleSerie.addSeason(sampleSeason);

        assertEquals(sampleSerie, sampleSeason.getSerie());

        sampleSerie.setSeasons(sampleSeasons);

        assertThat(sampleSeasons).allMatch(season ->
                season.getSerie() != null && season.getSerie().equals(sampleSerie)
        );
    }



}