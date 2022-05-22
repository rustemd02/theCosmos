package ru.kpfu.itis.services;

import ru.kpfu.itis.models.dtos.MovieDto;
import ru.kpfu.itis.models.entities.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> findAll();

    Movie findById(Long id);

    Optional<Movie> findMovieBySeanceId(Long seanceId);

    Movie addMovie(MovieDto movieDto);

    void deleteMovie(Long movieId);

    Movie editMovie(MovieDto movieDto);
}
