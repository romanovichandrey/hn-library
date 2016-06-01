package by.romanovich.it.dao;

import by.romanovich.it.dao.exeptions.DaoException;
import by.romanovich.it.dao.interfaces.Dao;
import by.romanovich.it.pojos.Category;
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
 * Testing class CategoryDao.
 * @see CategoryDao
 * @author Romanovich Andrei
 * @version 1.0
 */
@ContextConfiguration("/beans-daoTest.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CategoryDaoTest extends Assert {

    @Autowired
    private Dao<Category, Long> categoryDao;

    private static Category category1 = null;

    private static Category category2 = null;

    private static Category category3 = null;

    /**
     * Initialize custom parameter for tests.
     */
    @BeforeClass
    public static void initUserDao() {
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
        try {
            categoryIdResult = categoryDao.add(category1);
            categoryDao.add(category2);
            categoryDao.add(category3);
        } catch (DaoException e) {
            e.printStackTrace();
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
            categoryDao.add(category1);
            categoryDao.add(category2);
            categoryDao.add(category3);
            categories = categoryDao.getAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        List<Category> categoryTest = Arrays.asList(category1, category2, category3);
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
            categoryDao.add(category1);
            categoryDao.add(category2);
            categoryDao.add(category3);
            categoryResult = categoryDao.get(category1.getId());
        } catch (DaoException e) {
            e.printStackTrace();
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
            categoryDao.update(category1);
            categoryResult = categoryDao.get(category1.getId());
        } catch (DaoException e) {
            e.printStackTrace();
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
            categoryDao.add(category3);
            categoryDao.delete(category3);
            categoryResult = categoryDao.get(category3.getId());
        } catch (DaoException e) {
            e.printStackTrace();
        }
        assertNull(categoryResult);

    }
}
