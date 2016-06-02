package by.romanovich.it.service.service;

import by.romanovich.it.dao.exceptions.DaoException;
import by.romanovich.it.dao.interfaces.Dao;
import by.romanovich.it.pojos.Book;
import by.romanovich.it.service.exceptions.ServiceErrorCode;
import by.romanovich.it.service.exceptions.ServiceException;
import by.romanovich.it.service.service.interfases.BookService;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementing BookService interface.
 * Processing business-logic for work with Book.
 * @see by.romanovich.it.service.service.interfases.BookService
 * @author Romanovich Angrei
 * @version 1.0
 */
@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

    private static Logger log = Logger.getLogger(BookServiceImpl.class);

    @Autowired
    private Dao<Book, Long> bookDao;

    public BookServiceImpl() {
    }

    @Override
    public Boolean updateBook(Book book) throws ServiceException {
        try {
            bookDao.update(book);
            log.info("Updating book:" + book);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_006);
        }
    }

    @Override
    public Book getBookById(Long id) throws ServiceException {
        Book book = null;
        try {
            book = bookDao.get(id);
            log.info("Getting book:" + book);
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_007);
        }
        return book;
    }

    @Override
    public List<Book> getAllBooks() throws ServiceException {
        List<Book> books = null;
        try {
            books = bookDao.getAll();
            log.info("Getting all books:" + books);
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_008);
        }
        return books;
    }

    @Override
    public Boolean deleteBook(Long id) throws ServiceException {
        try {
            Book book = bookDao.get(id);
            bookDao.delete(book);
            log.info("Deleting book:" + book);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_009);
        }
    }

    @Override
    public Boolean saveBook(Book book) throws ServiceException {
        try {
            bookDao.add(book);
            log.info("Adding book:" + book);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_010);
        }
    }

    @Override
    public Long getRowCountBooks() throws ServiceException {
        try {
            Query query = bookDao.getQuery("select count(distinct id) from Book");
            Long countResult = (Long) query.uniqueResult();
            return countResult;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_017);
        }
    }

    @Override
    public List<Book> findBooks(Integer start, Integer end) throws ServiceException {
        try {
            Query query = bookDao.getQuery("from Book");
            query.setFirstResult(start);
            query.setMaxResults(end);
            List<Book> bookList = query.list();
            return bookList;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_016);
        }
    }

    @Override
    public List<Book> findBook(String param) throws ServiceException {
        List<Book> bookList;
        param = '%' + param + '%';
        try {
            Query query = bookDao.getQuery("from Book b where b.name like  :param");
            query.setParameter("param", param);
            bookList = query.list();
            log.info("Finding books" + bookList);
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_000);
        }
        return bookList;
    }

}
