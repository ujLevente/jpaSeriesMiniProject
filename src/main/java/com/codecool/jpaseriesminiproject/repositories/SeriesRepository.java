package com.codecool.jpaseriesminiproject.repositories;

import com.codecool.jpaseriesminiproject.entities.Genre;
import com.codecool.jpaseriesminiproject.entities.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeriesRepository extends JpaRepository<Series, Long> {

}
