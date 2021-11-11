package repositories;

import models.User;

public interface UsersRepository extends CrudRepository<User> {
    User findByLogin(String login);
    User changeBalance(User user, int newBalance);
}
