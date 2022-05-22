package ru.kpfu.itis.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.models.dtos.BuyTicketDto;
import ru.kpfu.itis.models.dtos.SeanceDto;
import ru.kpfu.itis.models.entities.Seance;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.services.SeanceService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Transactional
@Controller
@RequestMapping(value = "/schedule")
public class ScheduleController {

    @Autowired
    private SeanceService seanceService;

    @Transactional
    @GetMapping
    public ModelAndView schedulePage(String redirect, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        if (redirect != null) {
            modelAndView.setViewName("redirect:/" + redirect);
            return modelAndView;
        }
        modelAndView.setViewName("schedule");
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

        List<Seance> seances = seanceService.findAll();
        modelAndView.addObject("seances", seances);
        return modelAndView;
    }

    @Transactional
    @GetMapping(value = "/seance")
    public ModelAndView seancePageGet(String redirect) {
        ModelAndView modelAndView = new ModelAndView();
        if (redirect != null) {
            modelAndView.setViewName("redirect:/" + redirect);
            return modelAndView;
        }
        modelAndView.setViewName("seance");
        modelAndView.addObject("signIn", "Выйти");
        modelAndView.addObject("profileLink", "/profile");
        modelAndView.addObject("register", "Профиль");
        modelAndView.addObject("signOutLink", "/logout");

        return modelAndView;
    }

    @Transactional
    @PostMapping(value = "/seance")
    public void seancePagePost(SeanceDto seanceDto, HttpServletResponse resp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        String seanceId = seanceDto.getSeanceId();

        if (seanceId != null && !seanceId.equals("null")) {
            Seance seance = seanceService.findById(Long.valueOf(seanceId)).get();
            String json = objectMapper.writeValueAsString(seance);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            System.out.println("json " +json);

            resp.getWriter().println(json);
        }

    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, value = "/seance/buy_ticket")
    public void buyTicket(Authentication authentication, BuyTicketDto buyTicketDto, HttpServletResponse response) throws IOException {
        String seanceId = buyTicketDto.getSeanceId();
        Boolean useCosmostar = buyTicketDto.getUseCosmostar();

        if (seanceId != null) {
            User user = (User) authentication.getPrincipal();
            Seance seance = seanceService.buyTicket(Long.valueOf(seanceId), user, useCosmostar);
            if (seance != null) {
                String json = "{}";
                response.getWriter().println(json);
            }
        }
    }


}
