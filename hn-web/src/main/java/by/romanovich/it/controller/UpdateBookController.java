package by.romanovich.it.controller;

import by.romanovich.it.pojos.Book;
import by.romanovich.it.pojos.Category;
import by.romanovich.it.pojos.User;
import by.romanovich.it.service.exeptions.ServiceExeption;
import by.romanovich.it.service.service.CategoryService;
import by.romanovich.it.service.service.CategoryServiceImpl;
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
import java.util.Enumeration;
import java.util.List;

public class UpdateBookController extends HttpServlet{

    private static final long serialVersionUID = 1L;
    private final static Logger logger = Logger.getLogger(UpdateBookController.class);

    private Session session;

    private HibernateUtil util = HibernateUtil.getUtil();

    public UpdateBookController() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        CategoryService categoryService = CategoryServiceImpl.getCategoryService();
        HttpSession httpSession = request.getSession(false);
        Session session = (Session)httpSession.getAttribute("hibernateSession");
        try {
            session = util.getSession();
            session.beginTransaction();
            List<Category> categoryList = categoryService.getAllCategories();
            session.getTransaction().commit();

            @SuppressWarnings("unchecked")
            Enumeration<String> params = request.getParameterNames();
            logger.info(params);
            String name = null;
            String description = null;
            String book_date = null;

            while(params.hasMoreElements()) {
                String param = params.nextElement();
                name = "name".equals(param)?request.getParameter(param):name;
                description = "description".equals(param)?request.getParameter(param):description;
                book_date = "book_date".equals(param)?request.getParameter(param):book_date;

            }

            request.setAttribute("name", name);
            request.setAttribute("description", description);
            request.setAttribute("book_date", book_date);
            request.setAttribute("categoryList", categoryList);


            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/updateBook.jsp");
            dispatcher.forward(request, response);
        } catch (ServiceExeption e) {
            logger.error("Cannot get all categories" + e);
            session.getTransaction().rollback();
        }

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Book book;
        User user = new User();
        Category category = new Category();

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String author = request.getParameter("author");
        String book_date = request.getParameter("book_date");
        Long id_cat = Long.parseLong(request.getParameter("id_cat"));
        Long id_user = Long.parseLong(request.getParameter("id_user"));

        user.setId(id_user);
        category.setId(id_cat);

        book = new Book(name, description, book_date);



        RequestDispatcher dispatcher = request.getRequestDispatcher("books");
        dispatcher.forward(request, response);
    }
}
