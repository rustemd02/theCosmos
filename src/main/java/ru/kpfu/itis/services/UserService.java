package ru.kpfu.itis.services;


import ru.kpfu.itis.models.entities.Cosmostar;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.models.forms.AuthForm;
import ru.kpfu.itis.models.forms.UserForm;

import javax.servlet.http.Cookie;
import java.util.Optional;

public interface UserService {
    User register(UserForm userForm);
    Cookie signIn(AuthForm authForm);
    User findUserByCookieValue(String cookieValue);
    Optional<Cosmostar> findCardByUser (User user);
    Cosmostar cardInit (User user);
    boolean emailDoesntExist(String email);
}
