package by.romanovich.it.controller;

import by.romanovich.it.service.exeptions.ServiceExeption;
import by.romanovich.it.service.service.BookService;
import by.romanovich.it.service.service.BookServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This controller delete for Book
 * @author Romanovich Andrei
 * @version 1.0
 */
public class DeleteBookController extends HttpServlet {

    private static Logger log = Logger.getLogger(DeleteBookController.class);

    public DeleteBookController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idResult = Long.parseLong(req.getParameter("id_book"));
        BookService bookService = BookServiceImpl.getBookService();
        if(idResult != null) {
            try {
                bookService.deleteBook(idResult);
            } catch (ServiceExeption e) {
                log.info("Cannot deleting book by id");
            }
        }
        req.getServletContext().getRequestDispatcher("/book").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
