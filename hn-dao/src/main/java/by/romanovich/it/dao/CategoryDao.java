package by.romanovich.it.dao;

import by.romanovich.it.pojos.Category;

/**
 * Extends BaseDao class. Creating BaseDao generic
 * @see by.romanovich.it.dao.BaseDao
 * @author Romanovich Andrey
 * @version 1.0
 */
public class CategoryDao extends BaseDao<Category, Long> {

    public CategoryDao(Class<Category> type) {
        super(type);
    }
}
