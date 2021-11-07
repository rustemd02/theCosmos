package services;

import form.LogInForm;
import form.UserForm;
import models.Auth;
import models.Cosmostar;
import models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.AuthRepository;
import repositories.CosmostarRepository;
import repositories.UsersRepository;

import javax.servlet.http.Cookie;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    private UsersRepository usersRepository;
    private AuthRepository authRepository;
    private CosmostarRepository cosmostarRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UsersRepository usersRepository, AuthRepository authRepository, CosmostarRepository cosmostarRepository) {
        this.usersRepository = usersRepository;
        this.authRepository = authRepository;
        this.cosmostarRepository = cosmostarRepository;
    }

    public UserServiceImpl(UsersRepository usersRepository, AuthRepository authRepository) {
        this.usersRepository = usersRepository;
        this.authRepository = authRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public User register(UserForm userForm) {
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPasswordHash(userForm.getPassword());

        String passwordHash = new BCryptPasswordEncoder().encode(userForm.getPassword());

        user.setPasswordHash(passwordHash);

        return usersRepository.save(user);
    }

    @Override
    public Cookie signIn(LogInForm loginForm) {
        User user = usersRepository.findByLogin(loginForm.getEmail());

        if (user != null) {
            if (passwordEncoder.matches(loginForm.getPassword(), user.getPasswordHash())) {
                System.out.println("Вход выполнен!");
                String cookieValue = UUID.randomUUID().toString();
                System.out.println(cookieValue);
                Cookie cookie = new Cookie("auth", cookieValue);
                Auth auth = new Auth();
                auth.setUser(user);
                auth.setCookieValue(cookieValue);
                authRepository.save(auth);
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
        Auth auth = authRepository.findByCookieValue(cookieValue);
        if (auth != null) {
            return auth.getUser();
        } else {
            return null;
        }
    }

    @Override
    public Cosmostar findCardByUser(User user) {
        return cosmostarRepository.findCardByUser(user);
    }

    @Override
    public Cosmostar cardInit(User user) {
       return cosmostarRepository.cardInit(user);
    }
}

