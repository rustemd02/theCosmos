package ru.kpfu.itis.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class MainPageController {

    @GetMapping
    public ModelAndView getMainPage(Authentication authentication, String redirect) {
        ModelAndView modelAndView = new ModelAndView();
        if (redirect != null) {
            modelAndView.setViewName("redirect:/" + redirect);
            return modelAndView;
        }

        modelAndView.setViewName("main");
        if (authentication != null && authentication.isAuthenticated()) {
            modelAndView.addObject("signIn", "Выйти");
            modelAndView.addObject("profileLink", "/profile");
            modelAndView.addObject("register", "Профиль");
            modelAndView.addObject("signOutLink", "/logout");
        } else {
            modelAndView.addObject("signIn", "Вход");
            modelAndView.addObject("profileLink", "/register");
            modelAndView.addObject("register", "Регистрация");
            modelAndView.addObject("signOutLink", "/login");
        }

        return modelAndView;
    }
}
