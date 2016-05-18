package by.romanovich.it.controller;

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

public class UserController extends HttpServlet {

    private static Logger log = Logger.getLogger(UserController.class);

    private static final Long serialVersionUID = 1L;

    public UserController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserServiceImpl.getUserService();
        int page = 1;
        int recordsPage = 5;
        try {
            if(req.getParameter("page") != null)
                page = Integer.parseInt(req.getParameter("page"));
            Long totalCount = userService.getRowCountBooks();
            List<User> userList = userService.findBooks(((page - 1) * recordsPage), recordsPage);
            log.info("Getting totalCount" + totalCount);
            int noOfPages = (int) Math.ceil(totalCount * 1.0 / recordsPage);
            req.setAttribute("userList", userList);
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);
            req.setAttribute("userList", userList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/allUsers.jsp");
            dispatcher.forward(req, resp);
        } catch (ServiceExeption e) {
            log.error("Cannot get all users" + e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
