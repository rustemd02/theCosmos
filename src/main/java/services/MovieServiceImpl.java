package services;

import models.Movie;
import repositories.MoviesRepository;

import java.util.List;

public class MovieServiceImpl implements MovieService {

    private MoviesRepository moviesRepository;

    public MovieServiceImpl(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @Override
    public List<Movie> findAll() {
        return moviesRepository.findAll();
    }
}
