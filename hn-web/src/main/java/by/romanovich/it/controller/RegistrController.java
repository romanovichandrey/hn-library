package by.romanovich.it.controller;

import by.romanovich.it.pojos.Adress;
import by.romanovich.it.pojos.User;
import by.romanovich.it.service.exeptions.ServiceExeption;
import by.romanovich.it.service.service.UserService;
import by.romanovich.it.service.service.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class RegistrController extends HttpServlet {

    private static Logger log = Logger.getLogger(RegistrController.class);

    public RegistrController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/registr.jsp");
            dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserServiceImpl.getUserService();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String telephone = req.getParameter("telephone");
        String email = req.getParameter("email");
        String street = req.getParameter("street");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String country = req.getParameter("country");

        if(login.length() > 0 && password.length() > 0 && firstname.length() > 0 && lastname.length() > 0 &&
                telephone.length() > 0 && email.length() > 0 && street.length() > 0 && city.length() > 0 &&
                country.length() > 0 ){
            Adress adress = new Adress(street, city, state, country);
            User user = new User(firstname, lastname, telephone, email, login, password);
            user.setAdress(adress);
            adress.setUser(user);
            boolean status;
            try {
                List<User> users = userService.getAllUsers();
                for(User resultUser : users) {
                    if((user.getLogin()).compareToIgnoreCase(resultUser.getLogin()) == 0) {
                        req.getServletContext().getRequestDispatcher("/WEB-INF/errorJsp/errorRegistrLogin.jsp")
                                .forward(req, resp);
                    } else {
                        status = userService.saveUser(user);
                        if(status) {
                            req.getSession().setAttribute("user", user);
                            req.getServletContext().getRequestDispatcher("/book").forward(req, resp);
                        }
                    }
                }
            } catch (ServiceExeption e) {
                log.error("Cannot save user:", e);
            }
        } else
            req.getServletContext().getRequestDispatcher("/WEB-INF/errorJsp/errorRegistr.jsp")
                    .forward(req, resp);
    }
}
