package services;

import models.Movie;
import models.Seance;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> findAll();
    Optional<Movie> findById(Long id);
    Optional<Movie> findMovieBySeanceId(Long seanceId);
}
