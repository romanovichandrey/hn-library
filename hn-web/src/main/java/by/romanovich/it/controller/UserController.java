package by.romanovich.it.controller;

import by.romanovich.it.pojos.Book;
import by.romanovich.it.pojos.User;
import by.romanovich.it.service.exeptions.ServiceExeption;
import by.romanovich.it.service.service.UserService;
import by.romanovich.it.service.service.UserServiceImpl;
import by.romanovich.it.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController extends HttpServlet {

    private static Logger log = Logger.getLogger(UserController.class);

    private static final Long serialVersionUID = 1L;

    public UserController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserServiceImpl.getUserService();
        HibernateUtil util = HibernateUtil.getUtil();
        HttpSession httpSession = req.getSession(false);
        Session session = (Session) httpSession.getAttribute("hibernateSession");
        try {
            session = util.getSession();
            session.beginTransaction();
            List<User> userList = userService.getAllUsers();
            session.getTransaction().commit();
            session.disconnect();
            Set<Book> books = new HashSet<>();
            books.iterator();
            req.setAttribute("userList", userList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/allUsers.jsp");
            dispatcher.forward(req, resp);
        } catch (ServiceExeption e) {
            log.error("Cannot get all users" + e);
            session.getTransaction().rollback();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
