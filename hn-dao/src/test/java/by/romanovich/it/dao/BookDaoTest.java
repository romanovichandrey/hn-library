package by.romanovich.it.dao;

import by.romanovich.it.dao.exceptions.DaoException;
import by.romanovich.it.dao.interfaces.Dao;
import by.romanovich.it.pojos.Book;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Testing class BookDao.
 *
 * @author Romanovich Andrei
 * @version 1.0
 * @see BookDao
 */
@ContextConfiguration(locations = {"classpath:beans-daoTest.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BookDaoTest extends Assert {

    @Autowired()
    private Dao<Book, Long> bookDao;

    private static Book book1 = null;

    private static Book book2 = null;

    private static Book book3 = null;

    /**
     * Initialize custom parameter for tests.
     */
    @BeforeClass
    public static void initUserDao() {
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
        book1 = null;
        book2 = null;
        book3 = null;
    }

    /**
     * Testing bookDao.add()
     * @throws by.romanovich.it.dao.exceptions.DaoException
     */
    @Test
    public void testAdd() throws DaoException {
        Long bookIdResult1 = null;
        Long bookIdResult2 = null;
        Long bookIdResult3 = null;
        try {
            bookIdResult1 = bookDao.add(book1);
            bookIdResult2 = bookDao.add(book2);
            bookIdResult3 = bookDao.add(book3);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        assertNotNull(bookIdResult1);
        assertNotNull(bookIdResult2);
        assertNotNull(bookIdResult3);
    }

    /**
     * Testing bookDao.getAll()
     * @throws DaoException
     */
    @Test
    public void testGetAll() throws DaoException {
        List<Book> books = null;
        try {
            bookDao.add(book1);
            bookDao.add(book2);
            bookDao.add(book3);
            books = bookDao.getAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        List<Book> bookTest = Arrays.asList(book1, book2, book3);
        assertNotNull(books);
        assertEquals(books.size(), bookTest.size());
    }

    /**
     * Testing bookDao.get()
     * @throws DaoException
     */
    @Test
    public void testGet() throws DaoException {
        Book bookResult = null;
        try {
            bookDao.add(book1);
            bookDao.add(book2);
            bookDao.add(book3);
            bookResult = bookDao.get(book1.getId());
        } catch (DaoException e) {
            e.printStackTrace();
        }
        assertNotNull(bookResult);
        assertEquals(book1, bookResult);
    }

    /**
     * Testing bookDao.update()
     * @throws DaoException
     */
    @Test
    public void testUpdate() throws DaoException {
        book1.setName("Программирование на JAVA");
        Book bookResult = null;
        try {
            bookDao.update(book1);
            bookResult = bookDao.get(book1.getId());
        } catch (DaoException e) {
            e.printStackTrace();
        }
        assertNotNull(bookResult);
        assertEquals(book1, bookResult);

    }

    /**
     * Testing bookDao.delete()
     * @throws DaoException
     */
    @Test
    public void testDelete() throws DaoException {
        Book bookResult = book3;
        try {
            bookDao.delete(book3);
            bookResult = bookDao.get(book3.getId());
        } catch (DaoException e) {
            e.printStackTrace();
        }
        assertNull(bookResult);

    }
}
