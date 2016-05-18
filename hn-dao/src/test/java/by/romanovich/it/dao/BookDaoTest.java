package by.romanovich.it.dao;

import by.romanovich.it.dao.exeptions.DaoException;
import by.romanovich.it.pojos.Book;
import by.romanovich.it.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Testing class BookDao.
 * @see BookDao
 * @author Romanovich Andrei
 * @version 1.0
 */
public class BookDaoTest  extends Assert {

    private static BookDao bookDao = null;

    private static Book book1 = null;

    private static Book book2 = null;

    private static Book book3 = null;

    private static HibernateUtil hibernateUtil = null;

    private static Session session = null;

    /**
     * Initialize custom parameter for tests.
     */
    @BeforeClass
    public static void initUserDao() {
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

    }

    /**
     * Clear custom parameter.
     */
    @AfterClass
    public static void clearHibernateUtil() {
        hibernateUtil.sessionClose();
        hibernateUtil = null;
        bookDao = null;
        book1 = null;
        book2 = null;
        book3 = null;
    }

    /**
     * Testing bookDao.add()
     * @throws by.romanovich.it.dao.exeptions.DaoException
     */
    @Test
    public void testAdd() throws DaoException {
        Long bookIdResult = null;
        bookDao = new BookDao(Book.class);
        try {
            session.beginTransaction();
            bookIdResult = bookDao.add(book1);
            bookDao.add(book2);
            bookDao.add(book3);
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNotNull(bookIdResult);
    }

    /**
     * Testing bookDao.getAll()
     * @throws DaoException
     */
    @Test
    public void testGetAll() throws DaoException {
        List<Book> books = null;
        try {
            session.beginTransaction();
            books = bookDao.getAll();
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        List<Book> bookTest = Arrays.asList(book1, book2);
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
            session.beginTransaction();
            bookResult = bookDao.get(book1.getId());
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
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
            session.beginTransaction();
            bookDao.update(book1);
            bookResult = bookDao.get(book1.getId());
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
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
            session.beginTransaction();
            bookDao.delete(book3);
            bookResult = bookDao.get(book3.getId());
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNull(bookResult);

    }
}
