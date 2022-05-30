package ru.kpfu.itis.services.interfaces;

import ru.kpfu.itis.models.entities.Genre;

import java.util.Optional;

public interface GenreService {
    Optional<Genre> findGenresByMovieId(Long movieId);
}
