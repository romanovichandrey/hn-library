package by.romanovich.it.service.service;

import by.romanovich.it.dao.exceptions.DaoException;
import by.romanovich.it.dao.interfaces.Dao;
import by.romanovich.it.pojos.Autor;
import by.romanovich.it.pojos.Book;
import by.romanovich.it.service.exceptions.ServiceException;
import by.romanovich.it.service.service.interfases.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Testing class BookServiceImpl.
 * @author Romanovich Andrei
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest extends Assert {

    @Mock
    private Dao<Book, Long> bookDao;

    @InjectMocks
    private BookService bookService = new BookServiceImpl();

    private Book book1= null;

    private Book book2= null;

    private Book book3= null;

    private Autor autor1 = null;

    private Autor autor2 = null;

    private Autor autor3 = null;

    private List<Book> bookList;

    /**
     * Initialize custom parameter for tests.
     */
    @Before
    public void initAutorDao() {

        MockitoAnnotations.initMocks(this);

        String firstname_num1 = "Брюс";
        String lastname_num1 = "Эккель";

        String firstname_num2 = "Александр";
        String lastname_num2 = "Пушкин";

        String firstname_num3 = "Джеймс";
        String lastname_num3 = "Гаскин";

        autor1 = new Autor(firstname_num1, lastname_num1);
        autor2 = new Autor(firstname_num2, lastname_num2);
        autor3 = new Autor(firstname_num3, lastname_num3);

        String name_num1 = "Thinking in JAVA";
        String description_num1 = "Very very gook book";
        String yearPublishing_num1 = "2010";

        String name_num2 = "Администрирование Novel";
        String description_num2 = "Very very gook book";
        String yearPublishing_num2 = "2006";

        String name_num3 = "Ромео и Джульета";
        String description_num3 = "Very very gook book";
        String yearPublishing_num3 = "1816";

        book1 = new Book(name_num1, description_num1, yearPublishing_num1);
        book2 = new Book(name_num2, description_num2, yearPublishing_num2);
        book3 = new Book(name_num3, description_num3, yearPublishing_num3);

        bookList = Arrays.asList(book1, book2, book3);
    }

    /**
     * Testing bookService.updateBook()
     */
    @Test
    public void testUpdateBook() {
        book1.setName(book2.getName());
        try {
            bookService.updateBook(book1);
            verify(bookDao).update(book1);
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testing bookService.getBookById()
     */
    @Test
    public void testGetBookById() {
        try {
            when(bookDao.get(book1.getId())).thenReturn(book1);

            assertEquals(book1, bookService.getBookById(book1.getId()));
        }catch (DaoException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testing bookService.getAllBooks()
     */
    @Test
    public void testGetAllBooks() {
        try{
            when(bookDao.getAll()).thenReturn(bookList);

            assertNotNull(bookService.getAllBooks());
            assertEquals(bookList, bookService.getAllBooks());
        }catch (ServiceException e){
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    /**
     * Testing bookService.deleteBook()
     */
    @Test
    public void testDeleteBook() throws Exception {
        try {
            doNothing().when(bookDao).delete(book2);

            assertTrue(bookService.deleteBook(book2.getId()));
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }

    /**
     * Testing bookService.saveBook()
     */
    @Test
    public void testSaveBook() {
        try {
            bookService.saveBook(book3);

            verify(bookDao).add(book3);
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}