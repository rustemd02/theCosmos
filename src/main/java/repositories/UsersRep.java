package repositories;

import models.User;

public interface UsersRep extends CrudRep<User> {
    User findByLogin(String login);
}
