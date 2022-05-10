//package ru.kpfu.itis.repositories.old;
//
//import ru.kpfu.itis.mapper.RowMapperOld;
//import ru.kpfu.itis.models.entities.Movie;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class MoviesRepositoryImpl implements MoviesRepository{
//    private Connection connection;
//
//    private final String SQL_FIND_ALL = "SELECT * FROM movies;";
//    private final String SQL_FIND_BY_ID = "SELECT * FROM movies where movies.id = ?;";
//    private final String SQL_FIND_MOVIE_BY_SEANCE_ID = "SELECT movie_id FROM seance where id = ?;";
//
//
//    public MoviesRepositoryImpl(Connection connection) {
//        this.connection = connection;
//    }
//
//    @Override
//    public List<Movie> findAll() {
//        ResultSet resultSet = null;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
//            resultSet = preparedStatement.executeQuery();
//            List<Movie> movies = rowMapMovies.rowMap(resultSet);
//            return movies;
//        } catch (SQLException e) {
//
//        }
//        return null;
//    }
//
//    private RowMapperOld<List<Movie>> rowMapMovies = ((resultSet) -> {
//        List<Movie> movies = new ArrayList<>();
//        while (resultSet.next()) {
//            Movie movie = new Movie();
//            movie.setId(resultSet.getLong("id"));
//            movie.setTitle(resultSet.getString("title"));
//            movie.setDescription(resultSet.getString("description"));
//            movie.setPoster_link(resultSet.getString("poster_link"));
//            movie.setDirector(resultSet.getString("director"));
//            movie.setAge_restriction(resultSet.getString("age_restriction"));
//            movies.add(movie);
//        }
//        return movies;
//    });
//
//
//    @Override
//    public Optional<Movie> findById(Long id) {
//        ResultSet resultSet = null;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
//            preparedStatement.setLong(1, id);
//            resultSet = preparedStatement.executeQuery();
//            List<Movie> movies = rowMapMovies.rowMap(resultSet);
//            return movies.stream().findFirst();
//        } catch (SQLException e) {
//
//        }
//        return null;
//    }
//
//    @Override
//    public Optional<Movie> findMovieBySeanceId(Long seanceId){
//        ResultSet resultSet = null;
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_MOVIE_BY_SEANCE_ID);
//            preparedStatement.setInt(1, Math.toIntExact(seanceId));
//            resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            long id = resultSet.getInt("movie_id");
//            Optional<Movie> movie = findById(id);
//            return movie;
//        } catch (SQLException e) {
//
//        }
//        return null;
//    }
//
//
//
//    @Override
//    public Movie save(Movie movie) {
//        return null;
//    }
//
//    @Override
//    public void deleteById(Long id) {
//
//    }
//}
