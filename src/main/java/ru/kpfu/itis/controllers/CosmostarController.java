package ru.kpfu.itis.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.services.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CosmostarController {
    @Autowired
    private UserService usersService;

    @RequestMapping(method = RequestMethod.GET, value = "/cosmostar")
    public ModelAndView cosmostarPage(String redirect) {
        ModelAndView modelAndView = new ModelAndView();
        if (redirect != null) {
            modelAndView.setViewName("redirect:/" + redirect);
            return modelAndView;
        }
        modelAndView.setViewName("loyalty");
        modelAndView.addObject("signIn", "Выйти");
        modelAndView.addObject("profileLink", "/profile");
        modelAndView.addObject("register", "Профиль");
        modelAndView.addObject("signOutLink", "/logout");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/cosmostar")
    public ModelAndView cosmostar(Authentication authentication, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) authentication.getPrincipal();

        return modelAndView;
    }

}
