package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setHeaderBar(req);

        req.getRequestDispatcher("jsp/main.jsp").forward(req, resp);
    }

    static void setHeaderBar(HttpServletRequest req) {
        req.setAttribute("signIn", "Вход");
        req.setAttribute("profileLink", "/register");
        req.setAttribute("register", "Регистрация");
        req.setAttribute("signOutLink", "/login");
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    req.setAttribute("signIn", "Выйти");
                    req.setAttribute("profileLink", "/profile");
                    req.setAttribute("register", "Профиль");
                    req.setAttribute("signOutLink", "");
                }
            }
        } 
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/main.jsp").forward(req, resp);
    }
}
