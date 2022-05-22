package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.models.entities.Movie;

import java.util.List;
import java.util.Optional;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAll();

    Optional<Movie> findById(Long movieId);

    @Query("SELECT m from Movie m inner join Seance s on m.id = s.movie.id where s.id = ?1")
    Optional<Movie> findMovieBySeanceId(Long seanceId);

    List<Movie> findMoviesByDirectorId(Long directorId);

    List<Movie> findMoviesByGenreId(Long genreId);
}
