package ru.kpfu.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.models.entities.Cosmostar;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.services.interfaces.CosmostarService;
import ru.kpfu.itis.services.interfaces.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/cosmostar")
public class CosmostarController {
    @Autowired
    private UserService usersService;
    @Autowired
    private CosmostarService cosmostarService;

    @GetMapping
    public ModelAndView cosmostarPage(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) authentication.getPrincipal();

        if (cosmostarService.findCardByUserId(user.getId()).isEmpty()) {
            modelAndView.addObject("hasCosmostar", "У вас ещё нет карты Космостар");
            modelAndView.addObject("cosmostarBalance", "");
        } else {
            modelAndView.addObject("hasCosmostar", "Номер вашей карты Космостар: "
                    + cosmostarService.findCardByUserId(user.getId()).get().getId());
            modelAndView.addObject("cosmostarBalance", "У вас "
                    + cosmostarService.findCardByUserId(user.getId()).get().getPoints() + " бонусных баллов");

        }
        modelAndView.setViewName("loyalty");

        return modelAndView;
    }

    @PostMapping
    public ModelAndView cosmostar(Authentication authentication, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) authentication.getPrincipal();

        return modelAndView;
    }

    @PostMapping("/card_init")
    public void cosmostarInit(Authentication authentication, HttpServletResponse response) throws IOException {
        User user = (User) authentication.getPrincipal();
        Cosmostar cosmostar = usersService.cardInit(user);

        if (cosmostar != null) {
            String json = "{}";
            response.getWriter().println(json);
        }
    }

}
