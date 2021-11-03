package services;

import form.LogInForm;
import form.UserForm;
import models.User;

import javax.servlet.http.Cookie;

public interface UserService {
    User register(UserForm userForm);
    Cookie signIn(LogInForm loginForm);
    User findUserByCookieValue(String cookieValue);
}
