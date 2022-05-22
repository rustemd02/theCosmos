package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.models.entities.Genre;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("SELECT g from Genre g inner join Movie m on m.genre.id = g.id where m.id = ?1")
    Optional<Genre> findGenreByMovieId(Long movieId);
}
