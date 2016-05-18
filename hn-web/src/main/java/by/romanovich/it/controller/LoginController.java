package by.romanovich.it.controller;

import by.romanovich.it.pojos.User;
import by.romanovich.it.service.exeptions.ServiceExeption;
import by.romanovich.it.service.service.UserService;
import by.romanovich.it.service.service.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {

    private static Logger log = Logger.getLogger(LoginController.class);

    public LoginController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserServiceImpl.getUserService();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if(login != null && password != null) {
            try {
                User user = userService.getUserByLoginAndPassword(login, password);
                if(user != null) {
                    req.getSession().setAttribute("user", user);
                    req.getServletContext().getRequestDispatcher("/book")
                            .forward(req, resp);
                } else {
                    req.getServletContext().getRequestDispatcher("/WEB-INF/errorJsp/errorLogin.jsp")
                            .forward(req, resp);
                }
            } catch (ServiceExeption e) {
                log.error("Cannot get user by login and password");
            }

        } else
            req.getServletContext().getRequestDispatcher("/WEB-INF/errorJsp/errorLogin.jsp")
                    .forward(req, resp);
    }
}
