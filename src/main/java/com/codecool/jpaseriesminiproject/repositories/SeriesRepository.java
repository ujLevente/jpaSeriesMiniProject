package com.codecool.jpaseriesminiproject.repositories;

import com.codecool.jpaseriesminiproject.entities.Series;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SeriesRepository extends JpaRepository<Series, Long> {
}
