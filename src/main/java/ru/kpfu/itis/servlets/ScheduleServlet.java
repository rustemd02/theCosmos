//package ru.kpfu.itis.servlets;
//
//
//import ru.kpfu.itis.models.entities.Seance;
//import ru.kpfu.itis.repositories.old.*;
//
//import javax.servlet.ServletException;
//import javax.servlet.UnavailableException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.List;
//
//@WebServlet("/schedule")
//public class ScheduleServlet extends HttpServlet {
//
//    private MovieService movieService;
//    private SeanceService seanceService;
//    private UserService userService;
//
//    private final String URL = "jdbc:postgresql://localhost:5432/theCosmos";
//    private final String USERNAME = "postgres";
//    private final String PASSWORD = "";
//
//    @Override
//    public void init() throws ServletException {
//        try {
//            Class.forName("org.postgresql.Driver");
//            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//
//            MoviesRepository moviesRepository = new MoviesRepositoryImpl(connection);
//            movieService = new MovieServiceImpl(moviesRepository);
//            SeanceRepository seanceRepository = new SeanceRepositoryImpl(connection);
//            UsersRepository usersRepository = new UsersRepositoryImpl(connection);
//            seanceService = new SeanceServiceImpl(seanceRepository, usersRepository);
//
//        } catch (SQLException | ClassNotFoundException e) {
//            System.out.println("Unavailable");
//            throw new UnavailableException("Сайт недоступен!!!");
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        MainServlet.setHeaderBar(req);
//        req.setCharacterEncoding("UTF-8");
//
//        List<Seance> seances = seanceService.findAll();
//        req.setAttribute("seances", seances);
//
//        if (req.getParameter("seance_id") != null) {
//            req.getRequestDispatcher("jsp/movie.ftl").forward(req, resp);
//            return;
//        }
//
//
//        req.getRequestDispatcher("jsp/schedule.ftl").forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        req.getRequestDispatcher("jsp/schedule.ftl").forward(req, resp);
//    }
//}
