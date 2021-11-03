package servlets;

import models.User;
import repositories.AuthRepository;
import repositories.AuthRepositoryImpl;
import repositories.UsersRep;
import repositories.UsersRepImpl;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private UserService userService;

    private final String DB_URL = "jdbc:postgresql://localhost:5432/theCosmos";
    private final String DB_USERNAME = "postgres";
    private final String DB_PASSWORD = "";

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            UsersRep usersRepository = new UsersRepImpl(connection);
            AuthRepository authRepository = new AuthRepositoryImpl(connection);
            userService = new UserServiceImpl(usersRepository, authRepository);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookies[] = req.getCookies();

        for (Cookie cookie: cookies) {
            if (cookie.getName().equals("auth")) {
                User user = userService.findUserByCookieValue(cookie.getValue());
                if (user != null) {
                    req.setAttribute("name", user.getName());
                    req.setAttribute("signIn", "Выйти");
                    req.setAttribute("profileLink", "/profile");
                    req.setAttribute("register", "Профиль");
                    req.setAttribute("signOutLink", "");
                    req.getRequestDispatcher("jsp/profile.jsp").forward(req, resp);
                }
            }
        }

        resp.sendRedirect("/login");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/profile.jsp").forward(req, resp);
    }
}
