package ru.kpfu.itis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.models.dtos.MovieDto;
import ru.kpfu.itis.models.entities.Movie;
import ru.kpfu.itis.services.interfaces.MovieService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    //Get by id
    @GetMapping("/{id}")
    public ModelAndView getMovieById(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("aboutMovie");

        Movie movie = movieService.findById(id);
        modelAndView.addObject("movie", movie);
        logger.info(movie.toString());
        return modelAndView;
    }

    //Create
    @PostMapping("/add_movie")
    public void addMovie(MovieDto movieDto, HttpServletResponse response) throws IOException {
        Movie movie = movieService.addMovie(movieDto);
        getMoviePage();
        String json = "{}";
        response.getWriter().println(json);
    }

    //Update
    @PostMapping("/edit_movie")
    public void editMovie(MovieDto movieDto, HttpServletResponse response) throws IOException {
        movieService.editMovie(movieDto);
        String json = "{}";
        response.getWriter().println(json);
        getMovieById(movieDto.getId());
    }

    //Delete
    @DeleteMapping("/{id}/delete_movie")
    public void deleteMovie(@PathVariable Long id, HttpServletResponse response) throws IOException {
        movieService.deleteMovie(id);
        String json = "{}";
        response.getWriter().println(json);
        getMoviePage();
    }

}
