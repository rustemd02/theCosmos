package repositories;

import mapper.RowMapper;
import models.Cosmostar;
import models.Movie;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {
    private Connection connection;

    private final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE email=?";
    private final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private final String SQL_INSERT_USER = "insert into users(name, email, password_hash, balance) VALUES (?, ?, ?, ?)";

    public UsersRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID);
            preparedStatement.setString(1, id.toString());

            resultSet = preparedStatement.executeQuery();
            List<User> users = rowMapUsers.rowMap(resultSet);
            return users.stream().findFirst();
        } catch (SQLException throwables) {
            //
        }
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPasswordHash());
            preparedStatement.setInt(4, 1000);


            System.out.println(preparedStatement);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public User findByLogin(String email) {
        ResultSet resultSet = null;
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();
            List<User> users = rowMapUsers.rowMap(resultSet);
            return users.get(0);
        } catch (SQLException throwables) {
            //
        }
        return null;
    }

    private RowMapper<List<User>> rowMapUsers = ((resultSet) -> {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setPasswordHash(resultSet.getString("password_hash"));
            user.setBalance(resultSet.getInt("balance"));

            CosmostarRepositoryImpl cosmostarRepository = new CosmostarRepositoryImpl(connection);
            Cosmostar cosmostar = cosmostarRepository.findCardById(resultSet.getLong("cosmostar_id"));

            user.setCosmostar(cosmostar);
            users.add(user);
        }
        return users;
    });
}

