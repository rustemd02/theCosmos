package ru.kpfu.itis.services;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.models.entities.Auth;
import ru.kpfu.itis.models.entities.Cosmostar;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.models.forms.UserForm;
import ru.kpfu.itis.repositories.AuthRepository;
import ru.kpfu.itis.repositories.CosmostarRepository;
import ru.kpfu.itis.repositories.UsersRepository;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private CosmostarRepository cosmostarRepository;

    @Value("${sender.email}")
    private String senderEmail;

    @Value("${sender.password}")
    private String senderPassword;


    @Override
    public User register(UserForm userForm) throws MessagingException, UnsupportedEncodingException, EmailException {
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPasswordHash(userForm.getPassword());
        user.setBalance(500);

        String passwordHash = new BCryptPasswordEncoder().encode(userForm.getPassword());

        user.setPasswordHash(passwordHash);

        Email emailMessage = new SimpleEmail();

        emailMessage.setSmtpPort(465);
        emailMessage.setAuthenticator(new DefaultAuthenticator(senderEmail, senderPassword));
        emailMessage.setHostName("smtp.gmail.com");
        emailMessage.setSSLOnConnect(true);

        emailMessage.setFrom(senderEmail);
        emailMessage.setSubject("Регистрация");

        emailMessage.setMsg(user.getName() + ", поздравляем с успешной регистрацией на сайте Космоса!");

        emailMessage.addTo(user.getEmail());
        emailMessage.send();

        return usersRepository.save(user);
    }


    @Override
    public User findUserByCookieValue(String cookieValue) {
        Optional<Auth> optional = authRepository.findByCookieValue(cookieValue);

        Auth auth = null;
        if (optional.isPresent()) {
            auth = optional.get();
        }
        if (auth != null) {
            return auth.getUser();
        } else {
            return null;
        }
    }

    @Override
    public Optional<Cosmostar> findCardByUser(User user) {
        Long userId = user.getId();
        return cosmostarRepository.findCosmostarByUserId(userId);
    }

    @Override
    public Cosmostar cardInit(User user) {
        if (user.getCosmostar() == null && cosmostarRepository.findCosmostarByUserId(user.getId()).isEmpty()) {
            Cosmostar cosmostar = new Cosmostar();
            cosmostar.setUser(user);
            cosmostar.setPoints(300);
            cosmostarRepository.save(cosmostar);
            return cosmostar;
        }
       return null;
    }

    @Override
    public void setProfilePic(Long id, String fileName) {
        usersRepository.setProfilePic(id, fileName);
    }

    public boolean emailDoesntExist(String email) {
        return usersRepository.existsByEmail(email);
    }

}

