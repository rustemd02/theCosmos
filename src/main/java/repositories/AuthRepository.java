package repositories;

import models.Auth;

public interface AuthRepository extends CrudRepository<Auth> {
    Auth findByCookieValue(String cookieValue);
    Auth save(Auth auth);
}