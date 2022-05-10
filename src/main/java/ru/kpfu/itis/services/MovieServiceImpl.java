package ru.kpfu.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.models.entities.Movie;
import ru.kpfu.itis.repositories.MoviesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MoviesRepository moviesRepository;


    @Override
    public List<Movie> findAll() {
        return moviesRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return moviesRepository.findById(id);
    }

    @Override
    public Optional<Movie> findMovieBySeanceId(Long seanceId) {
        return moviesRepository.findMovieBySeanceId(seanceId);
    }


}
