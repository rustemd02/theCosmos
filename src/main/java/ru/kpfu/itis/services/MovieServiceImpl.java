package ru.kpfu.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.models.dtos.MovieDto;
import ru.kpfu.itis.models.entities.Director;
import ru.kpfu.itis.models.entities.Genre;
import ru.kpfu.itis.models.entities.Movie;
import ru.kpfu.itis.repositories.DirectorRepository;
import ru.kpfu.itis.repositories.GenreRepository;
import ru.kpfu.itis.repositories.MoviesRepository;
import ru.kpfu.itis.repositories.SeanceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private SeanceRepository seanceRepository;


    @Override
    public List<Movie> findAll() {
        List<Movie> movies = moviesRepository.findAll();
        for (Movie movie : movies) {
            Optional<Director> director = directorRepository.findDirectorByMovieId(movie.getId());
            director.get().setMovies(null);
            Optional<Genre> genre = genreRepository.findGenreByMovieId(movie.getId());
            genre.get().setMovies(null);
            movie.setDirector(director.get());
            movie.setGenre(genre.get());
            movie.setSeances(null);
        }
        return movies;
    }

    @Override
    public Movie findById(Long id) {
        Movie movie = moviesRepository.findById(id).get();
        Optional<Director> director = directorRepository.findDirectorByMovieId(movie.getId());
        director.get().setMovies(null);
        Optional<Genre> genre = genreRepository.findGenreByMovieId(movie.getId());
        genre.get().setMovies(null);
        movie.setDirector(director.get());
        movie.setGenre(genre.get());
        movie.setSeances(null);
        return movie;
    }

    @Override
    public Optional<Movie> findMovieBySeanceId(Long seanceId) {
        return moviesRepository.findMovieBySeanceId(seanceId);
    }

    @Override
    public Movie addMovie(MovieDto movieDto) {
        Director director = new Director();
        director.setName(movieDto.getDirector());
        directorRepository.save(director);
        Genre genre = new Genre();
        genre.setGenreName(movieDto.getGenre());
        genreRepository.save(genre);
        Movie movie = Movie.builder()
                .title(movieDto.getTitle())
                .description(movieDto.getDescription())
                .ageRestriction(movieDto.getAgeRestriction())
                .director(director)
                .genre(genre)
                .build();
        return moviesRepository.save(movie);
    }

    @Override
    public void deleteMovie(Long movieId) {
        Movie movie = moviesRepository.getById(movieId);
        moviesRepository.delete(movie);
    }

    @Override
    public Movie editMovie(MovieDto movieDto) {
        Movie movie = findById(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());

        Director director = new Director();
        director.setName(movieDto.getDirector());
        directorRepository.save(director);
        movie.setDirector(director);

        Genre genre = new Genre();
        genre.setGenreName(movieDto.getGenre());
        genreRepository.save(genre);
        movie.setGenre(genre);

        movie.setAgeRestriction(movieDto.getAgeRestriction());

        return moviesRepository.save(movie);
    }


}
