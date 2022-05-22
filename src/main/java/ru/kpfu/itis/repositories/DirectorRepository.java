package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.models.entities.Director;

import java.util.Optional;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

    @Query("SELECT d from Director d inner join Movie m on m.director.id = d.id where m.id = ?1")
    Optional<Director> findDirectorByMovieId(Long movieId);

}
