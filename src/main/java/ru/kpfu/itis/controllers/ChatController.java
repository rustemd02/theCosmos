package ru.kpfu.itis.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.models.entities.User;

@Controller
public class ChatController {
    @RequestMapping(method = RequestMethod.GET, value = "/movieChat")
    public ModelAndView cosmostarPage(Authentication authentication, String redirect) {
        ModelAndView modelAndView = new ModelAndView();
        if (redirect != null) {
            modelAndView.setViewName("redirect:/" + redirect);
            return modelAndView;
        }
        User user = (User) authentication.getPrincipal();
        modelAndView.setViewName("movieChat");
        modelAndView.addObject("user", user);
        modelAndView.addObject("signIn", "Выйти");
        modelAndView.addObject("profileLink", "/profile");
        modelAndView.addObject("register", "Профиль");
        modelAndView.addObject("signOutLink", "/logout");
        return modelAndView;
    }
}
