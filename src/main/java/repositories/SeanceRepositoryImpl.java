package repositories;

import mapper.RowMapper;
import models.Movie;
import models.Seance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SeanceRepositoryImpl implements SeanceRepository{
    private Connection connection;

    private final String SQL_FIND_ALL = "select * from seance;";
    private final String SQL_FIND_BY_ID = "select * from seance where id = ?;";
    private final String SQL_BUY_TICKET = "insert into users_seance (user_id, seance_id) VALUES (?,?);";

    public SeanceRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Seance> findAll() {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
            resultSet = preparedStatement.executeQuery();
            List<Seance> seances = rowMapSeances.rowMap(resultSet);
            return seances;
        } catch (SQLException e) {

        }
        return null;
    }

    @Override
    public Optional<Seance> findById(Long id) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            List<Seance> seances = rowMapSeances.rowMap(resultSet);
            return seances.stream().findFirst();
        } catch (SQLException e) {

        }
        return null;
    }

    @Override
    public Seance save(Seance seance) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }



    @Override
    public void buyTicket(Long seanceId, Long userId) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_BUY_TICKET, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, Math.toIntExact(userId));
            preparedStatement.setInt(2, Math.toIntExact(seanceId));
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException ignored) {

        }

    }

    private RowMapper<List<Seance>> rowMapSeances = ((resultSet) -> {
        List<Seance> seances = new ArrayList<>();
        while (resultSet.next()) {
            Seance seance = new Seance();
            seance.setId(resultSet.getLong("id"));

            MoviesRepositoryImpl moviesRepository = new MoviesRepositoryImpl(connection);
            Optional<Movie> movie = moviesRepository.findById(resultSet.getLong("movie_id"));

            seance.setMovie(movie.get());
            seance.setTime(resultSet.getTime("time"));
            seance.setTicketsAmount(resultSet.getInt("tickets_amount"));
            seance.setPrice(resultSet.getInt("price"));
            seances.add(seance);
        }
        return seances;
    });
}
