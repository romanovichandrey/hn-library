package by.romanovich.it.service.service;

import by.romanovich.it.pojos.Book;
import by.romanovich.it.service.exeptions.ServiceExeption;

import java.util.List;

/**
 * Interface for BookServiceImpl class.
 * @see by.romanovich.it.service.service.BookServiceImpl
 * @author Romanovich Andrei
 * @version 1.0
 */
public interface BookService {

    /**
     * This method updating book.
     * @param book
     * @return true if book successfully is updated.
     * @throws by.romanovich.it.service.exeptions.ServiceExeption
     */
    Boolean updateBook(Book book) throws ServiceExeption;

    /**
     * This method getting book by id.
     * @param id Book id
     * @return Book
     * @throws ServiceExeption
     */
    Book getBookById(Long id) throws ServiceExeption;

    /**
     * This method getting all books
     * @return list books
     * @throws ServiceExeption
     */
    List<Book> getAllBooks() throws ServiceExeption;

    /**
     * This method delete book by id.
     * @param book
     * @return true if book successfully is delete.
     * @throws ServiceExeption
     */
    Boolean deleteBook(Book book) throws ServiceExeption;

    /**
     * This method saving new book.
     * @param book
     * @return true if book successfully is save.
     * @throws ServiceExeption
     */
    Boolean saveBook(Book book) throws ServiceExeption;}