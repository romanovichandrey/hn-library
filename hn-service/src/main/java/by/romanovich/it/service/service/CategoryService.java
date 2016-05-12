package by.romanovich.it.service.service;

import by.romanovich.it.pojos.Category;
import by.romanovich.it.service.exeptions.ServiceExeption;

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
     * @throws by.romanovich.it.service.exeptions.ServiceExeption
     */
    Boolean updateCategory(Category category) throws ServiceExeption;

    /**
     * This method getting category by id.
     *
     * @param id Category id
     * @return Category
     * @throws ServiceExeption
     */
    Category getCategoryById(Long id) throws ServiceExeption;

    /**
     * This method getting all category
     *
     * @return list category
     * @throws ServiceExeption
     */
    List<Category> getAllCategories() throws ServiceExeption;

    /**
     * This method delete category by id.
     *
     * @param category
     * @return true if category successfully is delete.
     * @throws ServiceExeption
     */
    Boolean deleteCategory(Category category) throws ServiceExeption;

    /**
     * This method saving new category.
     *
     * @param category
     * @return true if category successfully is save.
     * @throws ServiceExeption
     */
    Boolean saveCategory(Category category) throws ServiceExeption;
}
