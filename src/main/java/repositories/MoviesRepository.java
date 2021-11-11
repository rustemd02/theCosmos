package repositories;

import models.Movie;
import models.Seance;

import java.util.Optional;

public interface MoviesRepository extends CrudRepository<Movie>{

    Optional<Movie> findMovieBySeanceId(Long seanceId);
}
