package by.romanovich.it.dao;


import by.romanovich.it.pojos.Book;
import org.apache.log4j.Logger;


/**
 * Extends BaseDao class. Creating BaseDao generic
 * @see by.romanovich.it.dao.BaseDao
 * @author Romanovich Andrey
 * @version 1.0
 */
public class BookDao extends BaseDao<Book, Long> {

    private static Logger log = Logger.getLogger(BookDao.class);

    public BookDao(Class<Book> type) {
        super(type);
    }

}
