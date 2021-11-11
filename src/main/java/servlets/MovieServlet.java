package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Movie;
import models.Seance;
import models.User;
import repositories.*;
import services.*;

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
import java.util.Optional;

@WebServlet("/movie")
public class MovieServlet extends HttpServlet {

    private MovieService movieService;
    private SeanceService seanceService;
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
            SeanceRepository seanceRepository = new SeanceRepositoryImpl(connection);
            UsersRepository usersRepository = new UsersRepositoryImpl(connection);
            seanceService = new SeanceServiceImpl(seanceRepository, usersRepository);
            AuthRepository authRepository = new AuthRepositoryImpl(connection);
            userService = new UserServiceImpl(usersRepository, authRepository);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Unavailable");
            throw new UnavailableException("Сайт недоступен!!!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MainServlet.setHeaderBar(req);


        req.getRequestDispatcher("jsp/movie.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String seanceId = req.getParameter("seance_id");
        Movie actualMovie;
        if (seanceId != null) {
            Optional<Movie> movie = movieService.findMovieBySeanceId(Long.valueOf(seanceId));
            actualMovie = movie.get();
            String json = objectMapper.writeValueAsString(actualMovie);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            System.out.println(json);
            resp.getWriter().println(json);
        }

        String buyTicket = req.getParameter("buyTicket");
        String buyTicketCosmostar = req.getParameter("buyTicketCosmostar");
        if ((buyTicket != null) || (buyTicketCosmostar != null)) {
            boolean useCosmostar = false;
            User user = findUser(req);
            if (buyTicketCosmostar != null) {
                useCosmostar = true;
                buyTicket = buyTicketCosmostar;
            }
            Seance seance = seanceService.buyTicket(Long.valueOf(buyTicket), user, useCosmostar);
            if (seance != null) {
                String json = "{}";
                resp.getWriter().println(json);
            }
        }

        // req.getRequestDispatcher("jsp/movie.jsp").forward(req, resp);
    }

    private User findUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("auth")) {
                return userService.findUserByCookieValue(cookie.getValue());
            }

        }
        return null;
    }
}
