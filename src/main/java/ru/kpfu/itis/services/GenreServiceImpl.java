package ru.kpfu.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.models.entities.Genre;
import ru.kpfu.itis.repositories.GenreRepository;
import ru.kpfu.itis.services.interfaces.GenreService;

import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Optional<Genre> findGenresByMovieId(Long movieId) {
        return genreRepository.findGenreByMovieId(movieId);
    }
}
