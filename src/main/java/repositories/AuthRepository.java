package repositories;

import models.Auth;

public interface AuthRepository extends CrudRep<Auth> {
    Auth findByCookieValue(String cookieValue);
}