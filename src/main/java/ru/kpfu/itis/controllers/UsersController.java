package ru.kpfu.itis.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import ru.kpfu.itis.models.dtos.AuthDto;
import ru.kpfu.itis.models.dtos.ProfilePicDto;
import ru.kpfu.itis.models.dtos.SignUpDto;
import ru.kpfu.itis.models.entities.User;
import ru.kpfu.itis.models.forms.AuthForm;
import ru.kpfu.itis.models.forms.UserForm;
import ru.kpfu.itis.services.UserService;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

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
    public ModelAndView signUp(SignUpDto signUpDto, HttpServletResponse response) {

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
        User user = (User) authentication.getPrincipal();
        modelAndView.setViewName("profile");
        modelAndView.addObject("user", user);
        modelAndView.addObject("userId", user.getId());
        modelAndView.addObject("name", user.getName());

        if (user.getCosmostar() == null) {
            modelAndView.addObject("hasCosmostar", "У вас ещё нет карты Космостар");
            modelAndView.addObject("cosmostarBalance", "");
        } else {
            modelAndView.addObject("hasCosmostar", "Номер вашей карты Космостар: " + user.getCosmostar().getId());
            modelAndView.addObject("cosmostarBalance", "У вас " + user.getCosmostar().getPoints() + " бонусных баллов");

        }
        modelAndView.addObject("cardBalance", user.getBalance() + " рублей");
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/profile/set_pic")
    public void setProfilePic(ProfilePicDto profilePicDto, HttpServletResponse response) throws IOException {
        MultipartFile file = profilePicDto.getProfilePicFile();
        logger.info("Загружаем файл");
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File(absoluteFilePath + fileName));
            usersService.setProfilePic(profilePicDto.getUserId(), fileName);
        } catch (IOException e) {
            logger.error("Произошла ошибка во время загрузки файла");
        }
        logger.info("Файл успешно загружен");
        String json = objectMapper.writeValueAsString(filePath + fileName);
        response.setContentType("application/json");
        response.getWriter().println(json);
    }

}
