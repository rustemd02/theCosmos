package ru.kpfu.itis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.models.dtos.MovieDto;
import ru.kpfu.itis.models.entities.Movie;
import ru.kpfu.itis.services.MovieService;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ModelAndView getMoviePage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("movies");
        modelAndView.addObject("signIn", "Выйти");
        modelAndView.addObject("profileLink", "/profile");
        modelAndView.addObject("register", "Профиль");
        modelAndView.addObject("signOutLink", "/logout");

        List<Movie> movies = movieService.findAll();
        modelAndView.addObject("movies", movies);

        return modelAndView;
    }

    @GetMapping("/add_movie")
    public ModelAndView addMoviePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("newMovie");
        modelAndView.addObject("signIn", "Выйти");
        modelAndView.addObject("profileLink", "/profile");
        modelAndView.addObject("register", "Профиль");
        modelAndView.addObject("signOutLink", "/logout");

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getMovieById(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("aboutMovie");

        Movie movie = movieService.findById(id);
        modelAndView.addObject("movie", movie);
        logger.info(movie.toString());
        return modelAndView;
    }

    @PostMapping("/add_movie")
    public void addMovie(MovieDto movieDto) {
        Movie movie = movieService.addMovie(movieDto);
        getMoviePage();
    }

    @PostMapping("/edit_movie")
    public void editMovie(MovieDto movieDto) {
        movieService.editMovie(movieDto);
        getMovieById(movieDto.getId());
    }

    @DeleteMapping("/{id}/delete_movie")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        getMoviePage();
    }

}
