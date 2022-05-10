package ru.kpfu.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.kpfu.itis.models.entities.Auth;
import ru.kpfu.itis.models.entities.Cosmostar;
import ru.kpfu.itis.models.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kpfu.itis.models.forms.AuthForm;
import ru.kpfu.itis.models.forms.UserForm;
import ru.kpfu.itis.repositories.AuthRepository;
import ru.kpfu.itis.repositories.CosmostarRepository;
import ru.kpfu.itis.repositories.UsersRepository;

import javax.servlet.http.Cookie;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private CosmostarRepository cosmostarRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User register(UserForm userForm) {
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPasswordHash(userForm.getPassword());
        user.setBalance(500);

        String passwordHash = new BCryptPasswordEncoder().encode(userForm.getPassword());

        user.setPasswordHash(passwordHash);

        return usersRepository.save(user);
    }

    @Override
    public Cookie signIn(AuthForm authForm) {

        User user = null;
        Optional<User> optional = usersRepository.findByEmail(authForm.getEmail());
        if (optional.isPresent()) user = optional.get();

        System.out.println(user);
        if (user != null) {
            if (passwordEncoder.matches(authForm.getPassword(), user.getPasswordHash())) {
                System.out.println("Вход выполнен!");
                String cookieValue = UUID.randomUUID().toString();

                Auth auth = new Auth();
                auth.setUser(user);
                auth.setCookieValue(cookieValue);
                Auth auth1 = authRepository.save(auth);
                System.out.println(auth1);
                Cookie cookie = new Cookie("auth", cookieValue);
                cookie.setMaxAge(10 * 60 * 60);
                return cookie;
            } else {
                System.out.println("Вход не выполнен!");
            }
        }
        return null;
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
            return cosmostarRepository.save(cosmostar);
        }
       return null;
    }

    public boolean emailDoesntExist(String email) {
        return usersRepository.existsByEmail(email);
    }

}

