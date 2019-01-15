package com.codecool.jpaseriesminiproject.repositories;

import com.codecool.jpaseriesminiproject.entities.Genre;
import com.codecool.jpaseriesminiproject.entities.Series;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.management.Query;
import java.util.Arrays;

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
        seriesRepository.save(serie);
        assertThat(seriesRepository.findAll()).hasSize(1);
    }

    @Test
    public void addedReviewsSavedWithSerie() {
        serie.setReviews(Arrays.asList("review1", "review2", "review3"));
        seriesRepository.save(serie);
        // TODO
    }
}