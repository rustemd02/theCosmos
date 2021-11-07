package servlets;


import models.Movie;
import repositories.MoviesRepository;
import repositories.MoviesRepositoryImpl;
import services.MovieService;
import services.MovieServiceImpl;
import services.UserService;

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
import java.util.List;

@WebServlet("/schedule")
public class ScheduleServlet extends HttpServlet {

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
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Unavailable");
            throw new UnavailableException("Сайт недоступен!!!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MainServlet.setHeaderBar(req);
        req.setCharacterEncoding("UTF-8");

        List<Movie> movies = movieService.findAll();
        req.setAttribute("movies", movies);

        req.getRequestDispatcher("jsp/schedule.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("jsp/schedule.jsp").forward(req, resp);
    }
}
