package by.romanovich.it.service.service;

import by.romanovich.it.pojos.Autor;
import by.romanovich.it.pojos.Book;
import by.romanovich.it.service.exeptions.ServiceExeption;
import by.romanovich.it.util.HibernateUtil;
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

    /**
     * Initialize custom parameter for tests.
     */
    @BeforeClass
    public static void initAutorDao() {

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


    }

    /**
     * Clear custom parameter.
     */
    @AfterClass
    public static void clearHibernateUtil() {
        HibernateUtil.getUtil().sessionClose();
        bookService = null;
        book1 = null;
        book2 = null;
        book3 = null;
        autor1 = null;
        autor2 = null;
        autor3 = null;
    }

    /**
     * Testing bookSercice.saveBook and bookServise.deleteBook()
     * and bookService.updateBook() and bookService.getAllBooks()
     * and bookService.getBookById().
     * @throws by.romanovich.it.service.exeptions.ServiceExeption
     */
    @Test
    public void testSaveBook() throws ServiceExeption {
        Boolean bookSaveResult1 = false;
        Boolean bookrSaveResult2 = false;
        Boolean bookSaveResult3 = false;
        Book bookIdResult = book1;
        Boolean bookDeleteResult = false;
        Boolean bookUpdateResult = false;
        List<Book> books = null;
        book1.setName("New name");
        bookService = BookServiceImpl.getBookService();
        try {
            book1.getAutors().add(autor1);
            book2.getAutors().add(autor2);
            book3.getAutors().add(autor3);
            bookSaveResult1 = bookService.saveBook(book1);
            bookrSaveResult2 = bookService.saveBook(book2);
            bookSaveResult3 = bookService.saveBook(book3);
            bookUpdateResult = bookService.updateBook(book1);
            bookIdResult = bookService.getBookById(book1.getId());
            books = bookService.getAllBooks();
            bookDeleteResult = bookService.deleteBook(book1.getId());
            assertTrue(bookUpdateResult);
        } catch (ServiceExeption e) {
            e.printStackTrace();
        }
        assertTrue(bookSaveResult1);
        assertTrue(bookrSaveResult2);
        assertTrue(bookSaveResult3);
        assertTrue(bookUpdateResult);
        assertNotNull(bookIdResult);
        assertEquals(book1, bookIdResult);
        assertNotNull(books);
        List<Book> bookTest = Arrays.asList(book1, book2, book3);
        assertEquals(bookTest, books);
        assertTrue(bookDeleteResult);
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
}