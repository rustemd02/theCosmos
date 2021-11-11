package services;

import models.Movie;
import models.Seance;
import repositories.MoviesRepository;

import java.util.List;
import java.util.Optional;

public class MovieServiceImpl implements MovieService {

    private MoviesRepository moviesRepository;

    public MovieServiceImpl(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

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
