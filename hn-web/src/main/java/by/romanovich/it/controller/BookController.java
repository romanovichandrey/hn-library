package by.romanovich.it.controller;

import by.romanovich.it.pojos.Autor;
import by.romanovich.it.pojos.Book;
import by.romanovich.it.service.exeptions.ServiceExeption;
import by.romanovich.it.service.service.BookService;
import by.romanovich.it.service.service.BookServiceImpl;
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

public class BookController extends HttpServlet {

    private static Logger log = Logger.getLogger(BookController.class);

    private static final Long serialVersionUID = 1L;

    public BookController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookService bookService = BookServiceImpl.getBookService();
        HibernateUtil util = HibernateUtil.getUtil();
        HttpSession httpSession = req.getSession(false);
        Session session = (Session)httpSession.getAttribute("hibernateSession");
        try {
            session = util.getSession();
            session.beginTransaction();
            List<Book> bookList = bookService.getAllBooks();
            session.getTransaction().commit();
            session.disconnect();
            Set<Autor> autors = new HashSet<>();
            autors.iterator();
            req.setAttribute("bookList", bookList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/allBooks.jsp");
            dispatcher.forward(req, resp);
        } catch (ServiceExeption e) {
            log.error("Cannot get all books" + e);
            session.getTransaction().rollback();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
