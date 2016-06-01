package by.romanovich.it.dao;


import by.romanovich.it.pojos.Book;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * Extends BaseDao class. Creating BaseDao generic
 * @see BaseDao
 * @author Romanovich Andrey
 * @version 1.0
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class BookDao extends BaseDao<Book, Long> {

    private static Logger log = Logger.getLogger(BookDao.class);



}
