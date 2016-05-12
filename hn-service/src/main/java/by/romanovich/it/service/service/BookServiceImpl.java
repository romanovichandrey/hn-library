package by.romanovich.it.service.service;

import by.romanovich.it.dao.BaseDao;
import by.romanovich.it.dao.BookDao;
import by.romanovich.it.dao.exeptions.DaoException;
import by.romanovich.it.pojos.Book;
import by.romanovich.it.service.exeptions.ServiceErrorCode;
import by.romanovich.it.service.exeptions.ServiceExeption;
import org.apache.log4j.Logger;

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

    private BaseDao<Book, Long> bookDao = null;

    private BookServiceImpl() {
        bookDao = new BookDao(Book.class);
    }

    private static BookServiceImpl getBookService() {
        if(bookService == null) {
            bookService = new BookServiceImpl();
        }
        return bookService;
    }

    @Override
    public Boolean updateBook(Book book) throws ServiceExeption {
        try {
            bookDao.update(book);
            log.info("Updating book:" + book);
            return true;
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_006);
        }
    }

    @Override
    public Book getBookById(Long id) throws ServiceExeption {
        Book book = null;
        try {
            book = bookDao.get(id);
            log.info("Getting book:" + book);
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_007);
        }
        return book;
    }

    @Override
    public List<Book> getAllBooks() throws ServiceExeption {
        List<Book> books = null;
        try {
            books = bookDao.getAll();
            for(Book book : books)
                log.info("Getting all books:" + book);
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_008);
        }
        return books;
    }

    @Override
    public Boolean deleteBook(Book book) throws ServiceExeption {
        try {
            bookDao.delete(book);
            log.info("Deleting book:" + book);
            return true;
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_009);
        }
    }

    @Override
    public Boolean saveBook(Book book) throws ServiceExeption {
        try {
            bookDao.add(book);
            log.info("Adding book:" + book);
            return true;
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_010);
        }
    }
}
