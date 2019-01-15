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

    private Series serie;

    @Before
    public void beforeEachInit() {
        serie = Series.builder()
                .title("Game of Thrones")
                .genre(Genre.ACTION)
                .build();
    }

    @Test
    public void testSaveWithOnlyTitle() {
        serie.setReviews(Arrays.asList("review1", "review2"));
        seriesRepository.save(serie);
        assertThat(seriesRepository.findAll()).hasSize(1);
    }

    @Test
    public void addedReviewsSavedWithSerie() {
        serie.setReviews(Arrays.asList("review1", "review2", "review3"));
        seriesRepository.save(serie);
        // TODO
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

}