package by.romanovich.it.dao;

import by.romanovich.it.dao.exeptions.DaoException;
import by.romanovich.it.pojos.Category;
import by.romanovich.it.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Testing class CategoryDao.
 * @see CategoryDao
 * @author Romanovich Andrei
 * @version 1.0
 */
public class CategoryDaoTest extends Assert {

    private static Dao<Category, Long> categoryDao = null;

    private static Category category1 = null;

    private static Category category2 = null;

    private static Category category3 = null;

    private static HibernateUtil hibernateUtil = null;

    private static Session session = null;

    /**
     * Initialize custom parameter for tests.
     */
    @BeforeClass
    public static void initUserDao() {
        hibernateUtil = HibernateUtil.getUtil();
        session = hibernateUtil.getSession();
        String name_num1 = "Фантастика";
        String name_num2 = "Программирование";
        String name_num3 = "Роман";
        category1 = new Category(name_num1);
        category2 = new Category(name_num2);
        category3 = new Category(name_num3);

    }

    /**
     * Clear custom parameter.
     */
    @AfterClass
    public static void clearHibernateUtil() {
        hibernateUtil.sessionClose();
        hibernateUtil = null;
        categoryDao = null;
        category1 = null;
        category2 = null;
        category3 = null;
    }

    /**
     * Testing categoryDao.add()
     * @throws by.romanovich.it.dao.exeptions.DaoException
     */
    @Test
    public void testAdd() throws DaoException {
        Long categoryIdResult = null;
        categoryDao = new CategoryDao(Category.class);
        try {
            session.beginTransaction();
            categoryIdResult = categoryDao.add(category1);
            categoryDao.add(category2);
            categoryDao.add(category3);
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNotNull(categoryIdResult);
    }

    /**
     * Testing categoryDao.getAll()
     * @throws DaoException
     */
    @Test
    public void testGetAll() throws DaoException {
        List<Category> categories = null;
        try {
            session.beginTransaction();
            categories = categoryDao.getAll();
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        List<Category> categoryTest = Arrays.asList(category1, category2);
        assertNotNull(categories);
        assertEquals(categories.size(), categoryTest.size());
    }

    /**
     * Testing categoryDao.get()
     * @throws DaoException
     */
    @Test
    public void testGet() throws DaoException {
        Category categoryResult = null;
        try {
            session.beginTransaction();
            categoryResult = categoryDao.get(category1.getId());
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNotNull(categoryResult);
        assertEquals(category1, categoryResult);
    }

    /**
     * Testing categoryDao.update()
     * @throws DaoException
     */
    @Test
    public void testUpdate() throws DaoException {
        category1.setName("Художественная");
        Category categoryResult = null;
        try {
            session.beginTransaction();
            categoryDao.update(category1);
            categoryResult = categoryDao.get(category1.getId());
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNotNull(categoryResult);
        assertEquals(category1, categoryResult);

    }

    /**
     * Testing categoryDao.delete()
     * @throws DaoException
     */
    @Test
    public void testDelete() throws DaoException {
        Category categoryResult = category3;
        try {
            session.beginTransaction();
            categoryDao.delete(category3);
            categoryResult = categoryDao.get(category3.getId());
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNull(categoryResult);

    }
}
