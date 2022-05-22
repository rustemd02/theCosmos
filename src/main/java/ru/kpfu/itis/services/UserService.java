package ru.kpfu.itis.services;


import org.apache.commons.mail.EmailException;
import ru.kpfu.itis.models.entities.Cosmostar;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.models.forms.UserForm;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

public interface UserService {
    User register(UserForm userForm) throws MessagingException, UnsupportedEncodingException, EmailException;

    User findUserByCookieValue(String cookieValue);
    Optional<Cosmostar> findCardByUser (User user);
    Cosmostar cardInit (User user);
    void setProfilePic(Long id, String fileName);
    boolean emailDoesntExist(String email);
}
