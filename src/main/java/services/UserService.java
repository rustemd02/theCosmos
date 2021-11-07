package services;

import form.LogInForm;
import form.UserForm;
import models.Cosmostar;
import models.User;

import javax.servlet.http.Cookie;

public interface UserService {
    User register(UserForm userForm);
    Cookie signIn(LogInForm loginForm);
    User findUserByCookieValue(String cookieValue);
    Cosmostar findCardByUser (User user);
    Cosmostar cardInit (User user);
}
