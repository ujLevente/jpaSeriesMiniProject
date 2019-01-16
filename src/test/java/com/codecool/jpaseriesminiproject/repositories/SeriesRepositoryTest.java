package com.codecool.jpaseriesminiproject.repositories;

import com.codecool.jpaseriesminiproject.entities.Genre;
import com.codecool.jpaseriesminiproject.entities.Season;
import com.codecool.jpaseriesminiproject.entities.Series;
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
public class SeriesRepositoryTest {

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    private Series serie;

    @Before
    public void beforeEachInit() {
        serie = Series.builder()
                .title("Game of Thrones")
                .genre(Genre.ACTION)
                .build();
    }

    @Test
    public void testSaveOneSimple() {
        seriesRepository.save(serie);
        assertThat(seriesRepository.findAll()).hasSize(1);
    }

    @Test
    public void idGivenAutomatically() {
        seriesRepository.save(serie);
        seriesRepository.save(new Series());
        seriesRepository.save(new Series());
        assertThat(seriesRepository.findAll()).allMatch(serie -> serie.getId() != null);
    }

    @Test
    public void addedReviewsSavedWithSerie() {
        List<String> reviewsToAdd = Arrays.asList("review1", "review2", "review3");
        serie.setReviews(reviewsToAdd);
        seriesRepository.save(serie);

        List<String> DbSerieReviews = seriesRepository.findAll().get(0).getReviews();

        assertEquals(DbSerieReviews.size(), reviewsToAdd.size());
    }

    @Test
    public void transientFieldNotPresentInDb() {
        seriesRepository.save(serie);
        assertThat(seriesRepository.findAll()).allMatch(serie -> serie.getNumOfUploadedSeasons() == 0L);
    }

    @Test
    public void numOfUploadedSeasonsIncrementsWithSeasonAdded() {
        int numOfAddedSeasons = 0;

        assertEquals(serie.getNumOfUploadedSeasons(), numOfAddedSeasons);

        List<Season> seasonsToAdd = new ArrayList<>(Arrays.asList(new Season(), new Season()));
        serie.setSeasons(seasonsToAdd);
        numOfAddedSeasons = seasonsToAdd.size();

        assertEquals(serie.getNumOfUploadedSeasons(), numOfAddedSeasons);

        serie.addSeason(new Season());
        numOfAddedSeasons++;

        assertEquals(serie.getNumOfUploadedSeasons(), numOfAddedSeasons);
    }

    @Test
    public void seasonPersistedAndDeletedWithSeries() {
        List<Season> seasonsToAdd = Arrays.asList(new Season(), new Season());

        serie.setSeasons(seasonsToAdd);
        seriesRepository.save(serie);

        assertThat(seasonRepository.findAll()).hasSize(seasonsToAdd.size());

        seriesRepository.deleteAll();

        assertThat(seasonRepository.findAll()).hasSize(0);
    }

    @Test
    public void serieFieldGetSetInSeason() {
        Season sampleSeason = new Season();
        List<Season> sampleSeasons = Arrays.asList(new Season(), new Season(), new Season());

        serie.addSeason(sampleSeason);

        assertEquals(serie, sampleSeason.getSerie());

        serie.setSeasons(sampleSeasons);

        assertThat(sampleSeasons).allMatch(season ->
                season.getSerie() != null && season.getSerie().equals(serie)
        );
    }

}