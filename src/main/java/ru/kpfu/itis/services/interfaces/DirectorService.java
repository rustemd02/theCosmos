package ru.kpfu.itis.services.interfaces;

import ru.kpfu.itis.models.entities.Director;

import java.util.Optional;

public interface DirectorService {
    Optional<Director> findDirectorByMovieId(Long movieId);
}
