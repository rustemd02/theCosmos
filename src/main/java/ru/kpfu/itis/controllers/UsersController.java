package ru.kpfu.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.models.dtos.AuthDto;
import ru.kpfu.itis.models.dtos.SignUpDto;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.models.forms.AuthForm;
import ru.kpfu.itis.models.forms.UserForm;
import ru.kpfu.itis.services.UserService;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UsersController {

    @Autowired
    private UserService usersService;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    private ModelAndView loginPage(String redirect) {
        ModelAndView modelAndView = new ModelAndView();
        if (redirect != null) {
            modelAndView.setViewName("redirect:/" + redirect);
            return modelAndView;
        }
        modelAndView.setViewName("login");

        modelAndView.addObject("signIn", "Вход");
        modelAndView.addObject("profileLink", "/register");
        modelAndView.addObject("register", "Регистрация");
        modelAndView.addObject("signOutLink", "/login");


        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    private ModelAndView authorise(AuthDto authDto, HttpServletResponse response) {
        AuthForm authForm = AuthForm.builder()
                .email(authDto.getEmail())
                .password(authDto.getPassword())
                .build();
        Cookie cookie = usersService.signIn(authForm);

        ModelAndView modelAndView = new ModelAndView();
        if (cookie != null) {
            response.addCookie(cookie);
            response.addCookie(new Cookie("username", authDto.getEmail()));
            modelAndView.addObject("signIn", "Выйти");
            modelAndView.addObject("profileLink", "/profile");
            modelAndView.addObject("register", "Профиль");
            modelAndView.addObject("signOutLink", "/logout");
            modelAndView.setViewName("redirect:/profile");
        } else {
            modelAndView.addObject("signInStatus", "Неправильный логин или пароль");
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    private ModelAndView signUpPage(String redirect) {
        ModelAndView modelAndView = new ModelAndView();
        if (redirect != null) {
            modelAndView.setViewName("redirect:/" + redirect);
            return modelAndView;
        }
        modelAndView.setViewName("register");

        modelAndView.addObject("signIn", "Вход");
        modelAndView.addObject("profileLink", "/register");
        modelAndView.addObject("register", "Регистрация");
        modelAndView.addObject("signOutLink", "/login");


        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    private ModelAndView signUp(SignUpDto signUpDto, HttpServletResponse response) {

        ModelAndView modelAndView = new ModelAndView();
        System.out.println(signUpDto.toString());

        String name = signUpDto.getName();
        String email = signUpDto.getEmail();
        String password = signUpDto.getPassword();
        String repassword = signUpDto.getRepassword();
        String status;

        if (email.length() == 0) {
            status = "Поле почты не должно быть пустым!";
            modelAndView.addObject("validation", status);
            modelAndView.setViewName("redirect:/signUp");
            return modelAndView;
        }
        if (password.length() < 8) {
            status = "Длина пароля - не менее 8 символов!";
            modelAndView.addObject("validation", status);
            modelAndView.setViewName("redirect:/signUp");
            return modelAndView;
        }
        if (password.equals(repassword)) {

            UserForm userForm = new UserForm();
            userForm.setUsername(name);
            userForm.setEmail(email);
            userForm.setPassword(password);

            User user = usersService.register(userForm);
            if (user != null) {
                AuthForm authForm = AuthForm.builder()
                        .email(user.getEmail())
                        .password(password)
                        .build();

                Cookie cookie = usersService.signIn(authForm);
                cookie.setMaxAge(10 * 60 * 60);

                response.addCookie(cookie);
                response.addCookie(new Cookie("username", name));
                modelAndView.setViewName("redirect:/profile");
                return modelAndView;
            } else {
                status = "Не удалось создать аккаунт!";
            }
        } else {
            status = "Пароли не совпадают!";
        }
        modelAndView.addObject("validation", status);
        modelAndView.setViewName("redirect:/signUp");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/profile")
    private ModelAndView profilePage(Authentication authentication, String redirect) {
        ModelAndView modelAndView = new ModelAndView();
        if (redirect != null) {
            modelAndView.setViewName("redirect:/" + redirect);
            return modelAndView;
        }
        User user = (User) authentication.getPrincipal();
        modelAndView.setViewName("profile");
        modelAndView.addObject("name", user.getName());
        modelAndView.addObject("signIn", "Выйти");
        modelAndView.addObject("profileLink", "/profile");
        modelAndView.addObject("register", "Профиль");
        modelAndView.addObject("signOutLink", "/logout");
        if (user.getCosmostar() == null) {
            modelAndView.addObject("hasCosmostar", "У вас ещё нет карты Космостар");
            modelAndView.addObject("cosmostarBalance", "");
        } else {
            modelAndView.addObject("hasCosmostar", "Номер вашей карты Космостар: " +user.getCosmostar().getId());
            modelAndView.addObject("cosmostarBalance", "У вас " + user.getCosmostar().getPoints() + " бонусных баллов");

        }
        modelAndView.addObject("cardBalance", user.getBalance() + " рублей");
        return modelAndView;
    }

}
