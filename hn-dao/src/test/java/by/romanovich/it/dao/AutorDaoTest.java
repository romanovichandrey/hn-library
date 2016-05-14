package by.romanovich.it.dao;

import by.romanovich.it.dao.exeptions.DaoException;
import by.romanovich.it.pojos.Autor;
import by.romanovich.it.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Testing class AutorDao.
 * @see by.romanovich.it.dao.AutorDaoTest
 * @author Romanovich Andrei
 * @version 1.0
 */
public class AutorDaoTest extends Assert {

    private static Dao<Autor, Long> autorDao = null;

    private static Autor autor1 = null;

    private static Autor autor2 = null;

    private static Autor autor3 = null;

    private static HibernateUtil hibernateUtil = null;

    private static Session session = null;

    /**
     * Initialize custom parameter for tests.
     */
    @BeforeClass
    public static void initUserDao() {
        hibernateUtil = HibernateUtil.getUtil();
        session = hibernateUtil.getSession();
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
        autorDao = null;
        autor1 = null;
        autor2 = null;
        autor3 = null;
    }

    /**
     * Testing autorDao.add()
     * @throws by.romanovich.it.dao.exeptions.DaoException
     */
    @Test
    public void testAdd() throws DaoException {
        Long autorIdResult = null;
        autorDao = new AutorDao(Autor.class);
        try {
            session.beginTransaction();
            autorIdResult = autorDao.add(autor1);
            autorDao.add(autor2);
            autorDao.add(autor3);
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNotNull(autorIdResult);
    }

    /**
     * Testing autorDao.getAll()
     * @throws DaoException
     */
    @Test
    public void testGetAll() throws DaoException {
        List<Autor> autors = null;
        try {
            session.beginTransaction();
            autors = autorDao.getAll();
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        List<Autor> autorTest = Arrays.asList(autor1, autor2);
        assertNotNull(autors);
        assertEquals(autors.size(), autorTest.size());
    }

    /**
     * Testing autorDao.get()
     * @throws DaoException
     */
    @Test
    public void testGet() throws DaoException {
        Autor autorResult = null;
        try {
            session.beginTransaction();
            autorResult = autorDao.get(autor1.getId());
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNotNull(autorResult);
        assertEquals(autor1, autorResult);
    }

    /**
     * Testing autorDao.update()
     * @throws DaoException
     */
    @Test
    public void testUpdate() throws DaoException {
        autor1.setFirstname("Andrei");
        Autor autorResult = null;
        try {
            session.beginTransaction();
            autorDao.update(autor1);
            autorResult = autorDao.get(autor1.getId());
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNotNull(autorResult);
        assertEquals(autor1, autorResult);

    }

    /**
     * Testing autorDao.delete()
     * @throws DaoException
     */
    @Test
    public void testDelete() throws DaoException {
        Autor autorResult = autor3;
        try {
            session.beginTransaction();
            autorDao.delete(autor3);
            autorResult = autorDao.get(autor3.getId());
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNull(autorResult);

    }
}
