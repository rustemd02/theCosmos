package ru.kpfu.itis.services;

import ru.kpfu.itis.models.entities.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> findAll();
    Optional<Movie> findById(Long id);
    Optional<Movie> findMovieBySeanceId(Long seanceId);
}
