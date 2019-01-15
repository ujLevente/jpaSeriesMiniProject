package com.codecool.jpaseriesminiproject.repositories;

import com.codecool.jpaseriesminiproject.entities.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}
