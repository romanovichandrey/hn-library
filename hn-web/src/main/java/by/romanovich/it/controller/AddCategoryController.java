package by.romanovich.it.controller;

import by.romanovich.it.pojos.Category;
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

public class AddCategoryController extends HttpServlet {

    private static Logger log = Logger.getLogger(AddCategoryController.class);

    private HibernateUtil hibernateUtil = HibernateUtil.getUtil();

    private Session session = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/addCategory.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        HttpSession httpSession = req.getSession();
        session = (Session) httpSession.getAttribute("hibernateSession");
        CategoryService categoryService = CategoryServiceImpl.getCategoryService();
        Category category = new Category(name);
        try {
            session = hibernateUtil.getSession();
            session.beginTransaction();
            log.info("Adding category:" + category);
            categoryService.saveCategory(category);
            session.getTransaction().commit();
            session.disconnect();
        } catch (ServiceExeption e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/book");
        dispatcher.forward(req, resp);

    }
}
