package repositories;

import mapper.RowMapper;
import models.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MoviesRepositoryImpl implements MoviesRepository{
    private Connection connection;

    private final String FIND_ALL = "SELECT * FROM movies;";


    public MoviesRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Movie> findAll() {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            resultSet = preparedStatement.executeQuery();
            List<Movie> movies = rowMapProducts.rowMap(resultSet);
            return movies;
        } catch (SQLException e) {

        }
        return null;
    }

    private RowMapper<List<Movie>> rowMapProducts = ((resultSet) -> {
        List<Movie> movies = new ArrayList<>();
        while (resultSet.next()) {
            Movie movie = new Movie();
            movie.setId(resultSet.getLong("id"));
            movie.setTitle(resultSet.getString("title"));
            movie.setDescription(resultSet.getString("description"));
            movie.setPoster_link(resultSet.getString("poster_link"));
            movie.setDirector(resultSet.getString("director"));
            movie.setAge_restriction(resultSet.getString("age_restriction"));
            movies.add(movie);
        }
        return movies;
    });

    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Movie save(Movie movie) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
