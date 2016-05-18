package by.romanovich.it.controller;

import by.romanovich.it.pojos.Book;
import by.romanovich.it.service.exeptions.ServiceExeption;
import by.romanovich.it.service.service.BookService;
import by.romanovich.it.service.service.BookServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookController extends HttpServlet {

    private static Logger log = Logger.getLogger(BookController.class);

    private static final Long serialVersionUID = 1L;

    public BookController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookService bookService = BookServiceImpl.getBookService();
        int page = 1;
        int recordsPage = 5;
        try {
            if(req.getParameter("page") != null)
                page = Integer.parseInt(req.getParameter("page"));
            Long totalCount = bookService.getRowCountBooks();
            List<Book> bookList = bookService.findBooks(((page - 1) * recordsPage), recordsPage);
            log.info("Getting totalCount" + totalCount);
            int noOfPages = (int) Math.ceil(totalCount * 1.0 / recordsPage);
            req.setAttribute("bookList", bookList);
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);
            RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/allBooks.jsp");
            dispatcher.forward(req, resp);
        } catch (ServiceExeption e) {
            log.error("Cannot get all books" + e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
