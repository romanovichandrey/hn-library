package by.romanovich.it.service.service;

import by.romanovich.it.dao.BookDao;
import by.romanovich.it.dao.exeptions.DaoException;
import by.romanovich.it.pojos.Book;
import by.romanovich.it.service.exeptions.ServiceErrorCode;
import by.romanovich.it.service.exeptions.ServiceExeption;
import org.apache.log4j.Logger;
import org.hibernate.Query;

import java.util.List;

/**
 * Implementing BookService interface.
 * Processing business-logic for work with Book.
 * @see by.romanovich.it.service.service.BookService
 * @author Romanovich Angrei
 * @version 1.0
 */
public class BookServiceImpl implements BookService {

    private static Logger log = Logger.getLogger(BookServiceImpl.class);

    private static BookServiceImpl bookService = null;

    private BookDao bookDao = null;

    private BookServiceImpl() {
        bookDao = new BookDao(Book.class);
    }

    public synchronized static BookServiceImpl getBookService() {
        if(bookService == null) {
            bookService = new BookServiceImpl();
        }
        return bookService;
    }

    @Override
    public Boolean updateBook(Book book) throws ServiceExeption {
        try {
            bookDao.getHibernateSession().beginTransaction();
            bookDao.update(book);
            bookDao.getHibernateSession().getTransaction().commit();
            log.info("Updating book:" + book);
            return true;
        } catch (DaoException e) {
            bookDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_006);
        }
    }

    @Override
    public Book getBookById(Long id) throws ServiceExeption {
        Book book = null;
        try {
            bookDao.getHibernateSession().beginTransaction();
            book = bookDao.get(id);
            bookDao.getHibernateSession().getTransaction().commit();
            log.info("Getting book:" + book);
        } catch (DaoException e) {
            bookDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_007);
        }
        return book;
    }

    @Override
    public List<Book> getAllBooks() throws ServiceExeption {
        List<Book> books = null;
        try {
            bookDao.getHibernateSession().beginTransaction();
            books = bookDao.getAll();
            bookDao.getHibernateSession().getTransaction().commit();
            log.info("Getting all books:" + books);
        } catch (DaoException e) {
            bookDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_008);
        }
        return books;
    }

    @Override
    public Boolean deleteBook(Long id) throws ServiceExeption {
        try {
            bookDao.getHibernateSession().beginTransaction();
            Book book = bookDao.get(id);
            bookDao.delete(book);
            bookDao.getHibernateSession().getTransaction().commit();
            log.info("Deleting book:" + book);
            return true;
        } catch (DaoException e) {
            bookDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_009);
        }
    }

    @Override
    public Boolean saveBook(Book book) throws ServiceExeption {
        try {
            bookDao.getHibernateSession().beginTransaction();
            bookDao.add(book);
            bookDao.getHibernateSession().getTransaction().commit();
            log.info("Adding book:" + book);
            return true;
        } catch (DaoException e) {
            bookDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_010);
        }
    }

    @Override
    public Long getRowCountBooks() throws ServiceExeption {
        try {
            bookDao.getHibernateSession().beginTransaction();
            Query query = bookDao.getQuery("select count(distinct id) from Book");
            Long countResult = (Long) query.uniqueResult();
            bookDao.getHibernateSession().getTransaction().commit();
            return countResult;
        } catch (DaoException e) {
            bookDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_017);
        }
    }

    @Override
    public List<Book> findBooks(Integer start, Integer end) throws ServiceExeption {
        try {
            bookDao.getHibernateSession().beginTransaction();
            Query query = bookDao.getQuery("from Book");
            query.setFirstResult(start);
            query.setMaxResults(end);
            List<Book> bookList = query.list();
            bookDao.getHibernateSession().getTransaction().commit();
            return bookList;
        } catch (DaoException e) {
            bookDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_016);
        }
    }
}
