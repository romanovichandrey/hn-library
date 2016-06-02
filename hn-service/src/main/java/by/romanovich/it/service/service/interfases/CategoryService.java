package by.romanovich.it.service.service.interfases;

import by.romanovich.it.pojos.Category;
import by.romanovich.it.service.exceptions.ServiceException;

import java.util.List;

/**
 * Interface for CategoryServiceImpl class.
 * @see by.romanovich.it.service.service.CategoryServiceImpl
 * @author Romanovich Andrei
 * @version 1.0
 */
public interface CategoryService {

    /**
     * This method updating category.
     *
     * @param category
     * @return true if category successfully is updated.
     * @throws by.romanovich.it.service.exceptions.ServiceException
     */
    Boolean updateCategory(Category category) throws ServiceException;

    /**
     * This method getting category by id.
     *
     * @param id Category id
     * @return Category
     * @throws by.romanovich.it.service.exceptions.ServiceException
     */
    Category getCategoryById(Long id) throws ServiceException;

    /**
     * This method getting all category
     *
     * @return list category
     * @throws by.romanovich.it.service.exceptions.ServiceException
     */
    List<Category> getAllCategories() throws ServiceException;

    /**
     * This method delete category by id.
     *
     * @param category
     * @return true if category successfully is delete.
     * @throws by.romanovich.it.service.exceptions.ServiceException
     */
    Boolean deleteCategory(Category category) throws ServiceException;

    /**
     * This method saving new category.
     *
     * @param category
     * @return true if category successfully is save.
     * @throws by.romanovich.it.service.exceptions.ServiceException
     */
    Boolean saveCategory(Category category) throws ServiceException;
}
