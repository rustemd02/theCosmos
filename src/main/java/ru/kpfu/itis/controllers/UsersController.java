package ru.kpfu.itis.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.models.dtos.SignUpDto;
import ru.kpfu.itis.models.entities.Seance;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.models.forms.UserForm;
import ru.kpfu.itis.services.interfaces.CosmostarService;
import ru.kpfu.itis.services.interfaces.SeanceService;
import ru.kpfu.itis.services.interfaces.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
public class UsersController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Value("${custom.absolute.file.storage}")
    private String absoluteFilePath;

    @Value("${custom.file.storage}")
    private String filePath;

    @Autowired
    private UserService usersService;

    @Autowired
    private SeanceService seanceService;

    @Autowired
    private CosmostarService cosmostarService;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("login");

        modelAndView.addObject("signIn", "Вход");
        modelAndView.addObject("profileLink", "/register");
        modelAndView.addObject("register", "Регистрация");
        modelAndView.addObject("signOutLink", "/login");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ModelAndView authorise(HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("signIn", "Выйти");
        modelAndView.addObject("profileLink", "/profile");
        modelAndView.addObject("register", "Профиль");
        modelAndView.addObject("signOutLink", "/logout");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public ModelAndView signUpPage() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("register");

        modelAndView.addObject("signIn", "Вход");
        modelAndView.addObject("profileLink", "/register");
        modelAndView.addObject("register", "Регистрация");
        modelAndView.addObject("signOutLink", "/login");

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ModelAndView signUp(SignUpDto signUpDto, HttpServletResponse response)
            throws MessagingException, UnsupportedEncodingException, EmailException {

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
            userForm.setName(name);
            userForm.setEmail(email);
            userForm.setPassword(password);

            User user = usersService.register(userForm);
            if (user != null) {
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
    public ModelAndView profilePage(Authentication authentication, String redirect) {
        ModelAndView modelAndView = new ModelAndView();
        if (redirect != null) {
            modelAndView.setViewName("redirect:/" + redirect);
            return modelAndView;
        }
        logger.info("Зашли в профиль");
        User user = (User) authentication.getPrincipal();
        modelAndView.setViewName("profile");
        List<Seance> seances = seanceService.findSeancesByUserId(user.getId());
        seanceService.seanceConfigure(seances);
        modelAndView.addObject("seances", seances);
        modelAndView.addObject("user", user);
        modelAndView.addObject("userId", user.getId());
        modelAndView.addObject("name", user.getName());

        if (cosmostarService.findCardByUserId(user.getId()).isEmpty()) {
            modelAndView.addObject("hasCosmostar", "У вас ещё нет карты Космостар");
            modelAndView.addObject("cosmostarBalance", "");
        } else {
            modelAndView.addObject("hasCosmostar", "Номер вашей карты Космостар: "
                    + cosmostarService.findCardByUserId(user.getId()).get().getId());
            modelAndView.addObject("cosmostarBalance", "У вас "
                    + cosmostarService.findCardByUserId(user.getId()).get().getPoints() + " бонусных баллов");

        }
        modelAndView.addObject("cardBalance", user.getBalance() + " рублей");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/profile/set_pic")
    public ModelAndView setProfilePic(MultipartFile profilePic, Authentication authentication) throws IOException {
        User user = (User) authentication.getPrincipal();
        logger.info("Загружаем файл");
        String fileName = profilePic.getOriginalFilename();
        try {
            profilePic.transferTo(new File(absoluteFilePath + fileName));
            fileName = "/resources/uploads/" + profilePic.getOriginalFilename();
            usersService.setProfilePic(user.getId(), fileName);
        } catch (IOException e) {
            logger.error("Произошла ошибка во время загрузки файла");
        }
        logger.info("Файл успешно загружен");
        return new ModelAndView("redirect:/profile");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/profile/get_user")
    public void getUser(Authentication authentication, HttpServletResponse response) throws IOException {
        User user = (User) authentication.getPrincipal();
        String json = objectMapper.writeValueAsString(user);
        response.setContentType("application/json");
        response.getWriter().println(json);
    }


}
