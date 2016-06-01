package by.romanovich.it.service.service;

import by.romanovich.it.dao.exeptions.DaoException;
import by.romanovich.it.dao.interfaces.Dao;
import by.romanovich.it.pojos.Category;
import by.romanovich.it.service.exeptions.ServiceErrorCode;
import by.romanovich.it.service.exeptions.ServiceException;
import by.romanovich.it.service.service.interfases.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementing CategoryService interface.
 * Processing business-logic for work with Category.
 * @see by.romanovich.it.service.service.interfases.CategoryService
 * @author Romanovich Angrei
 * @version 1.0
 */
@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private static Logger log = Logger.getLogger(CategoryServiceImpl.class);

    @Autowired
    private Dao<Category, Long> categoryDao;

    @Override
    public Boolean updateCategory(Category category) throws ServiceException {
        try {
            categoryDao.update(category);
            log.info("Updating category:" + category);
            return true;
        } catch (DaoException e) {
            throw  new ServiceException(e, ServiceErrorCode.HN_SERV_011);
        }
    }

    @Override
    public Category getCategoryById(Long id) throws ServiceException {
        Category category = null;
        try {            
            category = categoryDao.get(id);
            log.info("Getting category:" + category);
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_012);
        }
        return category;
    }

    @Override
    public List<Category> getAllCategories() throws ServiceException {
        List<Category> categories = null;
        try {
            categories = categoryDao.getAll();
            log.info("Getting all categories:" + categories);
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_013);
        }
        return categories;
    }

    @Override
    public Boolean deleteCategory(Category category) throws ServiceException {
        try {
            categoryDao.delete(category);
            log.info("Deleting category:" + category);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_014);
        }
    }

    @Override
    public Boolean saveCategory(Category category) throws ServiceException {
        try {
            categoryDao.add(category);
            log.info("Adding category:" + category);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_015);
        }
    }
}
