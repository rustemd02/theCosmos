package repositories;

import mapper.RowMapper;
import models.Cosmostar;
import models.User;

import java.sql.*;
import java.util.Optional;
import java.util.UUID;

public class CosmostarRepositoryImpl implements CosmostarRepository{

    private Connection connection;

    private final String SQL_FIND_CARD_BY_USER = "select * " +
            "from cosmostar_card inner join users u on cosmostar_card.id = u.cosmostar_id where u.id = ?;";
    private final String SQL_CARD_INIT = "insert into cosmostar_card (id, points) values (?, ?);";
    private final String SQL_FIND_CARD_BY_ID = "select * from cosmostar_card where id = ?;";


    public CosmostarRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Cosmostar findCardByUser(User user) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CARD_BY_USER);
            preparedStatement.setLong(1, user.getId());
            resultSet = preparedStatement.executeQuery();
            Cosmostar cosmostar = authRowMapper.rowMap(resultSet);
            return cosmostar;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Cosmostar findCardById(Long cosmostarId) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CARD_BY_ID);
            preparedStatement.setLong(1, cosmostarId);
            resultSet = preparedStatement.executeQuery();
            Cosmostar cosmostar = authRowMapper.rowMap(resultSet);
            return cosmostar;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Cosmostar cardInit(User user) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CARD_INIT, Statement.RETURN_GENERATED_KEYS);
            double cosmostarID = (int)(Math.random()*((99999999-999)+1))+999;
            preparedStatement.setLong(1, (long) cosmostarID);
            preparedStatement.setInt(2, 500);
            preparedStatement.setLong(3, user.getId());
            resultSet = preparedStatement.executeQuery();
            Cosmostar cosmostar = authRowMapper.rowMap(resultSet);
            return cosmostar;
        } catch (SQLException ignored) {

        }
        return null;
    }

    private RowMapper<Cosmostar> authRowMapper = (resultSet) -> {
        if (resultSet.next()) {
            Cosmostar cosmostar = new Cosmostar();
            cosmostar.setId(resultSet.getLong("id"));
            cosmostar.setPoints(resultSet.getInt("points"));
            return cosmostar;
        } else {
            return null;
        }
    };
}
