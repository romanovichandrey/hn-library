package by.romanovich.it.service.service;

import by.romanovich.it.dao.CategoryDao;
import by.romanovich.it.dao.Dao;
import by.romanovich.it.dao.exeptions.DaoException;
import by.romanovich.it.pojos.Category;
import by.romanovich.it.service.exeptions.ServiceErrorCode;
import by.romanovich.it.service.exeptions.ServiceExeption;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Implementing CategoryService interface.
 * Processing business-logic for work with Category.
 * @see by.romanovich.it.service.service.CategoryService
 * @author Romanovich Angrei
 * @version 1.0
 */
public class CategoryServiceImpl implements CategoryService {

    private static Logger log = Logger.getLogger(CategoryServiceImpl.class);

    private static CategoryServiceImpl categoryService = null;

    private Dao<Category, Long> categoryDao = null;

    private CategoryServiceImpl() {
        categoryDao = new CategoryDao(Category.class);
    }

    public synchronized static CategoryServiceImpl getCategoryService() {
        if(categoryService == null) {
            categoryService = new CategoryServiceImpl();
        }
        return categoryService;
    }

    @Override
    public Boolean updateCategory(Category category) throws ServiceExeption {
        try {
            categoryDao.getHibernateSession().beginTransaction();
            categoryDao.update(category);
            categoryDao.getHibernateSession().getTransaction().commit();
            log.info("Updating category:" + category);
            return true;
        } catch (DaoException e) {
            categoryDao.getHibernateSession().getTransaction().rollback();
            throw  new ServiceExeption(e, ServiceErrorCode.HN_SERV_011);
        }
    }

    @Override
    public Category getCategoryById(Long id) throws ServiceExeption {
        Category category = null;
        try {
            categoryDao.getHibernateSession().beginTransaction();
            category = categoryDao.get(id);
            categoryDao.getHibernateSession().getTransaction().commit();
            log.info("Getting category:" + category);
        } catch (DaoException e) {
            categoryDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_012);
        }
        return category;
    }

    @Override
    public List<Category> getAllCategories() throws ServiceExeption {
        List<Category> categories = null;
        try {
            categoryDao.getHibernateSession().beginTransaction();
            categories = categoryDao.getAll();
            categoryDao.getHibernateSession().getTransaction().commit();
            log.info("Getting all categories:" + categories);
        } catch (DaoException e) {
            categoryDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_013);
        }
        return categories;
    }

    @Override
    public Boolean deleteCategory(Category category) throws ServiceExeption {
        try {
            categoryDao.getHibernateSession().beginTransaction();
            categoryDao.delete(category);
            categoryDao.getHibernateSession().getTransaction().commit();
            log.info("Deleting category:" + category);
            return true;
        } catch (DaoException e) {
            categoryDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_014);
        }
    }

    @Override
    public Boolean saveCategory(Category category) throws ServiceExeption {
        try {
            categoryDao.getHibernateSession().beginTransaction();
            categoryDao.add(category);
            categoryDao.getHibernateSession().getTransaction().commit();
            log.info("Adding category:" + category);
            return true;
        } catch (DaoException e) {
            categoryDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_015);
        }
    }
}
