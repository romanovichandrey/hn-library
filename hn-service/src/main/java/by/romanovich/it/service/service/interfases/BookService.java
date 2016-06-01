package by.romanovich.it.service.service.interfases;

import by.romanovich.it.pojos.Book;
import by.romanovich.it.service.exeptions.ServiceException;

import java.util.List;

/**
 * Interface for BookServiceImpl class.
 * @see by.romanovich.it.service.service.BookServiceImpl
 * @author Romanovich Andrei
 * @version 1.0
 */
public interface BookService {

    /**
     * This method updating book by id.
     * @param book Long id
     * @return true if book successfully is updated.
     * @throws by.romanovich.it.service.exeptions.ServiceException
     */
    Boolean updateBook(Book book) throws ServiceException;

    /**
     * This method getting book by id.
     * @param id Book id
     * @return Book
     * @throws by.romanovich.it.service.exeptions.ServiceException
     */
    Book getBookById(Long id) throws ServiceException;

    /**
     * This method getting all books
     * @return list books
     * @throws by.romanovich.it.service.exeptions.ServiceException
     */
    List<Book> getAllBooks() throws ServiceException;

    /**
     * This method delete book by id.
     * @param id Long id
     * @return true if book successfully is delete.
     * @throws by.romanovich.it.service.exeptions.ServiceException
     */
    Boolean deleteBook(Long id) throws ServiceException;

    /**
     * This method saving new book.
     * @param book
     * @return true if book successfully is save.
     * @throws by.romanovich.it.service.exeptions.ServiceException
     */
    Boolean saveBook(Book book) throws ServiceException;

    /**
     * This method getting books by start and end positions.
     * @param start Integer start
     * @param end Integer end
     * @return list books
     * @throws by.romanovich.it.service.exeptions.ServiceException
     */
    List<Book> findBooks(Integer start, Integer end) throws ServiceException;

    /**
     * This method getting count books.
     * @return Integer
     * @throws by.romanovich.it.service.exeptions.ServiceException
     */
    Long getRowCountBooks() throws ServiceException;
}
