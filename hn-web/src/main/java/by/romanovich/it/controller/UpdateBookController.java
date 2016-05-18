package by.romanovich.it.controller;

import by.romanovich.it.pojos.Autor;
import by.romanovich.it.pojos.Book;
import by.romanovich.it.pojos.Category;
import by.romanovich.it.pojos.User;
import by.romanovich.it.service.exeptions.ServiceExeption;
import by.romanovich.it.service.service.*;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class UpdateBookController extends HttpServlet{

    private static final long serialVersionUID = 1L;

    private final static Logger logger = Logger.getLogger(UpdateBookController.class);

    public UpdateBookController() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CategoryService categoryService = CategoryServiceImpl.getCategoryService();
        BookService bookService = BookServiceImpl.getBookService();
        Long idResult = Long.parseLong(request.getParameter("id_book"));
        try {
            List<Category> categoryList = categoryService.getAllCategories();
            Book book = bookService.getBookById(idResult);
            Set<Autor> autorSet = book.getAutors();
            request.setAttribute("categoryList", categoryList);
            request.setAttribute("autorSet", autorSet);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/updateBook.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceExeption e) {
            logger.error("Cannot get all categories" + e);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = BookServiceImpl.getBookService();
        CategoryService categoryService = CategoryServiceImpl.getCategoryService();
        UserService userService = UserServiceImpl.getUserService();
        Book book = new Book();
        Category category = null;
        Autor autor = new Autor();
        User user = null;
        Long idBookResult = Long.parseLong(request.getParameter("id_book"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String book_date = request.getParameter("book_date");
        String autor_firstname = request.getParameter("autor_firstname");
        String autor_lastname = request.getParameter("autor_lastname");
        Long idCat = Long.parseLong(request.getParameter("id_cat"));
        Long idUser = Long.parseLong(request.getParameter("id_user"));
        try {
            book.setId(idBookResult);
            book.setName(name);
            book.setDescription(description);
            book.setYearPublishing(book_date);
            autor.setFirstname(autor_firstname);
            autor.setLastname(autor_lastname);
            book.getAutors().add(autor);
            category = categoryService.getCategoryById(idCat);
            book.setCategory(category);
            user = userService.getUserById(idUser);
            book.setUser(user);
            bookService.updateBook(book);
            request.getServletContext().getRequestDispatcher("/book").forward(request, response);
        } catch (ServiceExeption e) {
            logger.error("Cannot get book by id");
        }
    }
}
