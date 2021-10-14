package servlets;

import form.LogInForm;
import repositories.AuthRepository;
import repositories.AuthRepositoryImpl;
import repositories.UsersRep;
import repositories.UsersRepImpl;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/login")
public class SignInServlet extends HttpServlet {

    private UserService usersService;
    private Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/theCosmos", "postgres", "7872");

            UsersRep usersRepository = new UsersRepImpl(connection);
            AuthRepository authRepository = new AuthRepositoryImpl(connection);
            usersService = new UserServiceImpl(usersRepository, authRepository);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Unavailable");
            throw new UnavailableException("Сайт недоступен!!!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("html/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("email");
        String password = request.getParameter("password");

        LogInForm loginForm = new LogInForm(login, password);
        Cookie cookie = usersService.signIn(loginForm);

        if (cookie != null) {
            response.addCookie(cookie);
            request.setAttribute("name", login);
            request.setAttribute("signIn", "Выйти");
            request.setAttribute("profileLink", "html/main.html");
            request.setAttribute("register", "Профиль");
            request.getRequestDispatcher("html/profile.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("html/login.jsp").forward(request, response);
        }

    }

}