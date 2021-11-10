package servlets;

import models.Cosmostar;
import models.User;
import repositories.*;
import services.MovieService;
import services.MovieServiceImpl;
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

@WebServlet("/cosmostar")
public class LoyaltyServlet extends HttpServlet {

    private MovieService movieService;
    private UserService userService;

    private final String URL = "jdbc:postgresql://localhost:5432/theCosmos";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "";

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            MoviesRepository moviesRepository = new MoviesRepositoryImpl(connection);
            movieService = new MovieServiceImpl(moviesRepository);
            UsersRepository usersRepository = new UsersRepositoryImpl(connection);
            AuthRepository authRepository = new AuthRepositoryImpl(connection);
            CosmostarRepository cosmostarRepository = new CosmostarRepositoryImpl(connection);
            userService = new UserServiceImpl(usersRepository, authRepository, cosmostarRepository);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Unavailable");
            throw new UnavailableException("Сайт недоступен!!!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MainServlet.setHeaderBar(req);
        Cookie[] cookies = req.getCookies();
        Cosmostar cosmostar = new Cosmostar();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    User user = userService.findUserByCookieValue(cookie.getValue());
                    cosmostar = userService.findCardByUser(user);
                }
            }
        }
        if (cosmostar != null) {

        }


        req.getRequestDispatcher("jsp/loyalty.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/loyalty.jsp").forward(req, resp);
    }
}
