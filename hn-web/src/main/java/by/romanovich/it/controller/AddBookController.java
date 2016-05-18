package by.romanovich.it.controller;

import by.romanovich.it.pojos.Autor;
import by.romanovich.it.pojos.Book;
import by.romanovich.it.pojos.Category;
import by.romanovich.it.pojos.User;
import by.romanovich.it.service.exeptions.ServiceExeption;
import by.romanovich.it.service.service.BookService;
import by.romanovich.it.service.service.BookServiceImpl;
import by.romanovich.it.service.service.CategoryService;
import by.romanovich.it.service.service.CategoryServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddBookController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final static Logger log = Logger.getLogger(AddBookController.class);

    public AddBookController() {
        super();

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CategoryService categoryService = CategoryServiceImpl.getCategoryService();

        try {
            List<Category> categoryList = categoryService.getAllCategories();
            request.setAttribute("categoryList", categoryList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/addBook.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceExeption e) {
            log.error("Cannot get all categories" + e);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = BookServiceImpl.getBookService();
        Book book;
        Autor autor;
        User user = new User();
        Category category = new Category();

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String book_date = request.getParameter("book_date");
        Long id_cat = Long.parseLong(request.getParameter("id_cat"));
        Long id_user = Long.parseLong(request.getParameter("id_user"));

        book = new Book(name, description, book_date);
        autor = new Autor(firstname, lastname);
        category.setId(id_cat);
        user.setId(id_user);

        try {
            book.setCategory(category);
            book.setUser(user);
            book.getAutors().add(autor);
            bookService.saveBook(book);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/book");
            dispatcher.forward(request, response);
        } catch (ServiceExeption e) {
            log.error("Cannot save book" + e);
        }

    }
}
