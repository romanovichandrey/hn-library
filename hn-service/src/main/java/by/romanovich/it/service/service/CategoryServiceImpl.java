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

    public static CategoryServiceImpl getCategoryService() {
        if(categoryService == null) {
            categoryService = new CategoryServiceImpl();
        }
        return categoryService;
    }

    @Override
    public Boolean updateCategory(Category category) throws ServiceExeption {
        try {
            categoryDao.update(category);
            log.info("Updating category:" + category);
            return true;
        } catch (DaoException e) {
            throw  new ServiceExeption(e, ServiceErrorCode.HN_SERV_011);
        }
    }

    @Override
    public Category getCategoryById(Long id) throws ServiceExeption {
        Category category = null;
        try {
            category = categoryDao.get(id);
            log.info("Getting category:" + category);
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_012);
        }
        return category;
    }

    @Override
    public List<Category> getAllCategories() throws ServiceExeption {
        List<Category> categories = null;
        try {
            categories = categoryDao.getAll();
            for(Category category : categories)
                log.info("Getting all categories:" + category);
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_013);
        }
        return categories;
    }

    @Override
    public Boolean deleteCategory(Category category) throws ServiceExeption {
        try {
            categoryDao.delete(category);
            log.info("Deleting category:" + category);
            return true;
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_014);
        }
    }

    @Override
    public Boolean saveCategory(Category category) throws ServiceExeption {
        try {
            categoryDao.add(category);
            log.info("Adding category:" + category);
            return true;
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_015);
        }
    }
}
