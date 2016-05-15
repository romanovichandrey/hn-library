package by.romanovich.it.dao;

import by.romanovich.it.pojos.Book;

/**
 * Extends BaseDao class. Creating BaseDao generic
 * @see by.romanovich.it.dao.BaseDao
 * @author Romanovich Andrey
 * @version 1.0
 */
public class BookDao extends BaseDao<Book, Long> {

    public BookDao(Class<Book> type) {
        super(type);
    }
}
