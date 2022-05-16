package ru.kpfu.itis.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
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
import javax.transaction.Transactional;
import java.io.IOException;

@Controller
@RequestMapping("/seance")
public class SeanceController {


}
