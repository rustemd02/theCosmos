package servlets;

import form.UserForm;
import repositories.UsersRep;
import repositories.UsersRepImpl;
import services.UserService;
import services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private UserService usersService;

    private final String DB_URL = "jdbc:postgresql://localhost:5432/theCosmos";
    private final String DB_USERNAME = "postgres";
    private final String DB_PASSWORD = "";

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            UsersRep usersRepository = new UsersRepImpl(connection);
            usersService = new UserServiceImpl(usersRepository);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        UserForm userForm = new UserForm();
        userForm.setEmail(req.getParameter("email"));
        userForm.setName(req.getParameter("name"));
        userForm.setPassword(req.getParameter("password"));

        usersService.register(userForm);
        resp.sendRedirect("/login");
       // req.getRequestDispatcher("html/register.html").forward(req, resp);
    }
}
