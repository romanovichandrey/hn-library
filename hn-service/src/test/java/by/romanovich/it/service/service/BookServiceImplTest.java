package by.romanovich.it.service.service;

import by.romanovich.it.pojos.Autor;
import by.romanovich.it.pojos.Book;
import by.romanovich.it.service.exeptions.ServiceExeption;
import by.romanovich.it.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Testing class BookServiceImpl.
 * @author Romanovich Andrei
 * @version 1.0
 */
public class BookServiceImplTest extends Assert {

    private static BookService bookService= null;

    private static Book book1= null;

    private static Book book2= null;

    private static Book book3= null;

    private static Autor autor1 = null;

    private static Autor autor2 = null;

    private static Autor autor3 = null;

    private static HibernateUtil hibernateUtil = null;

    private static Session session = null;

    /**
     * Initialize custom parameter for tests.
     */
    @BeforeClass
    public static void initAutorDao() {
        hibernateUtil = HibernateUtil.getUtil();
        session = hibernateUtil.getSession();
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

        String firstname_num1 = "Брюс";
        String lastname_num1 = "Эккель";

        String firstname_num2 = "Александр";
        String lastname_num2 = "Пушкин";

        String firstname_num3 = "Джеймс";
        String lastname_num3 = "Гаскин";

        autor1 = new Autor(firstname_num1, lastname_num1);
        autor2 = new Autor(firstname_num2, lastname_num2);
        autor3 = new Autor(firstname_num3, lastname_num3);
    }

    /**
     * Clear custom parameter.
     */
    @AfterClass
    public static void clearHibernateUtil() {
        hibernateUtil.sessionClose();
        hibernateUtil = null;
        bookService = null;
        book1 = null;
        book2 = null;
        book3 = null;
    }

    /**
     * Testing bookSercice.saveBook.
     * @throws by.romanovich.it.service.exeptions.ServiceExeption
     */
    @Test
    public void testSaveBook() throws ServiceExeption {
        Boolean bookSaveResult1 = false;
        Boolean bookrSaveResult2 = false;
        Boolean bookSaveResult3 = false;
        bookService = BookServiceImpl.getBookService();
        try {
            session.beginTransaction();
            book1.getAutors().add(autor1);
            book2.getAutors().add(autor2);
            book3.getAutors().add(autor3);
            bookSaveResult1 = bookService.saveBook(book1);
            bookrSaveResult2 = bookService.saveBook(book2);
            bookSaveResult3 = bookService.saveBook(book3);
            session.getTransaction().commit();
        } catch (ServiceExeption e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertTrue(bookSaveResult1);
        assertTrue(bookrSaveResult2);
        assertTrue(bookSaveResult3);
    }

    /**
     * Testing bookService.getBookService()
     */
    @Test
    public void testGetBookService() {
        bookService = BookServiceImpl.getBookService();
        assertNotNull(bookService);
        bookService = null;
        assertNull(bookService);

    }

    /**
     * Testing bookService.getAllBooks()
     * @throws by.romanovich.it.service.exeptions.ServiceExeption
     */
    @Test
    public void testGetAllBooks() throws ServiceExeption {
        List<Book> books = null;
        bookService = BookServiceImpl.getBookService();
        try {
            session.beginTransaction();
            books = bookService.getAllBooks();
            session.getTransaction().commit();
        } catch (ServiceExeption e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNotNull(books);
        List<Book> bookTest = Arrays.asList(book1, book2, book3);
        assertEquals(bookTest, books);
    }

    /**
     * Testing bookService.updateBook()
     * @throws ServiceExeption
     */
    @Test
    public void testUpdateBook() throws ServiceExeption {
        Boolean bookUpdateResult = false;
        book1.setName("New name");
        bookService = BookServiceImpl.getBookService();
        try {
            session.beginTransaction();
            bookUpdateResult = bookService.updateBook(book1);
            session.getTransaction().commit();
        } catch (ServiceExeption e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertTrue(bookUpdateResult);

    }

    /**
     * Testing bookService.getBookById()
     * @throws ServiceExeption
     */
    @Test
    public void testGetBookById() throws ServiceExeption {
        Book bookIdResult = book1;
        bookService = BookServiceImpl.getBookService();
        try {
            session.beginTransaction();
            bookIdResult = bookService.getBookById(book1.getId());
            session.getTransaction().commit();
        } catch (ServiceExeption e) {
            e.printStackTrace();
            session.getTransaction().commit();
        }
        assertNotNull(bookIdResult);
        assertEquals(book1, bookIdResult);
    }

    /**
     * Testing bookService.deleteBook();
     * @throws ServiceExeption
     */
    @Test
    public void testDeleteBook() throws ServiceExeption {
        Boolean bookDeleteResult = false;
        bookService = BookServiceImpl.getBookService();
        try {
            session.beginTransaction();
            bookDeleteResult = bookService.deleteBook(book3);
            session.getTransaction().commit();
        } catch (ServiceExeption e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertTrue(bookDeleteResult);

    }
}