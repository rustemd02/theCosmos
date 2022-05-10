package ru.kpfu.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.models.entities.Seance;
import ru.kpfu.itis.services.SeanceService;
import ru.kpfu.itis.services.UserService;

import java.util.List;

@Controller
public class SeancesController {

    @Autowired
    private UserService usersService;

    @Autowired
    private SeanceService seanceService;

    @RequestMapping(method = RequestMethod.GET, value = "/schedule")
    public ModelAndView cosmostarPage(String redirect) {
        ModelAndView modelAndView = new ModelAndView();
        if (redirect != null) {
            modelAndView.setViewName("redirect:/" + redirect);
            return modelAndView;
        }
        modelAndView.setViewName("schedule");
        modelAndView.addObject("signIn", "Выйти");
        modelAndView.addObject("profileLink", "/profile");
        modelAndView.addObject("register", "Профиль");
        modelAndView.addObject("signOutLink", "/logout");

        List<Seance> seances = seanceService.findAll();
        modelAndView.addObject("seances", seances);
        return modelAndView;
    }
}
