package by.romanovich.it.dao;

import by.romanovich.it.dao.exeptions.DaoException;
import by.romanovich.it.pojos.Adress;
import by.romanovich.it.pojos.User;
import by.romanovich.it.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Testing class UserDao.
 * @see UserDaoImpl
 * @author Romanovich Andrei
 * @version 1.0
 */
public class UserDaoImplTest extends Assert{

    private static UserDao userDao = null;

    private static Adress adress1 = null;

    private static Adress adress2 = null;

    private static Adress adress3 = null;

    private static User user1 = null;

    private static User user2 = null;

    private static User user3 = null;

    private static HibernateUtil hibernateUtil = null;

    private static Session session = null;

    /**
     * Initialize custom parameter for tests.
     */
    @BeforeClass
    public static void initUserDao() {
        hibernateUtil = HibernateUtil.getUtil();
        session = hibernateUtil.getSession();
        String firstname_num1 = "Andrei";
        String lastname_num1 = "Romanovich";
        String telephone_num1 = "80292842087";
        String email_num1 = "romanovichandrei@mail.ru";
        String login_num1 = "admin";
        String password_num1 = "admin";
        String street_num1 = "Asanalieva";
        String city_num1 = "Minsk";
        String state_num1 = null;
        String country_num1 = "Belarus";
        adress1 = new Adress(street_num1, city_num1, state_num1, country_num1);
        user1 = new User(firstname_num1, lastname_num1, telephone_num1, email_num1, login_num1, password_num1);

        String firstname_num2 = "Sergei";
        String lastname_num2 = "Kravchenco";
        String telephone_num2 = "80334567856";
        String email_num2 = "kravchenco@gmail.com";
        String login_num2 = "user1";
        String password_num2 = "test";
        String street_num2 = "Serova";
        String city_num2 = "Minsk";
        String state_num2 = null;
        String country_num2 = "Belarus";
        adress2 = new Adress(street_num2, city_num2, state_num2, country_num2);
        user2 = new User(firstname_num2, lastname_num2, telephone_num2, email_num2, login_num2, password_num2);

        String firstname_num3 = "Sveta";
        String lastname_num3 = "Kravchenco";
        String telephone_num3 = "80334567856";
        String email_num3 = "kravchenco@gmail.com";
        String login_num3 = "user1";
        String password_num3 = "test";
        String street_num3 = "Serova";
        String city_num3 = "Minsk";
        String state_num3 = null;
        String country_num3 = "Belarus";
        adress3 = new Adress(street_num3, city_num3, state_num3, country_num3);
        user3 = new User(firstname_num3, lastname_num3, telephone_num3, email_num3, login_num3, password_num3);
    }

    /**
     * Clear custom parameter.
     */
    @AfterClass
    public static void clearHibernateUtil() {
        hibernateUtil.sessionClose();
        hibernateUtil = null;
        userDao = null;
        user1 = null;
        user2 = null;
        user3 = null;
        adress1 = null;
        adress2 = null;
        adress3 = null;
    }

    /**
     * Testing userDao.add()
     * @throws DaoException
     */
    @Test
    public void testAdd() throws DaoException {
        Long userIdResult = null;
        userDao = new UserDaoImpl(User.class);
        try {
            user1.setAdress(adress1);
            adress1.setUser(user1);
            user2.setAdress(adress2);
            adress2.setUser(user2);
            user3.setAdress(adress3);
            adress3.setUser(user3);
            userIdResult = userDao.add(user1);
            userDao.add(user2);
            userDao.add(user3);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        assertNotNull(userIdResult);
    }

    /**
     * Testing userDao.getAll()
     * @throws DaoException
     */
    @Test
    public void testGetAll() throws DaoException {
        List<User> users = null;
        try {
            session.beginTransaction();
            users = userDao.getAll();
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        List<User> userTest = Arrays.asList(user1, user3);
        assertNotNull(users);
        assertEquals(users.size(), userTest.size());
    }

    /**
     * Testing userDao.get()
     * @throws DaoException
     */
    @Test
    public void testGet() throws DaoException {
        User userResult = null;
        try {
            session.beginTransaction();
            userResult = userDao.get(user1.getId());
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNotNull(userResult);
        assertEquals(user1, userResult);
    }

    /**
     * Testing userDao.update()
     * @throws DaoException
     */
    @Test
    public void testUpdate() throws DaoException {
        user1.setPassword("test");
        User userResult = null;
        try {
            session.beginTransaction();
            userDao.update(user1);
            userResult = userDao.get(user1.getId());
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNotNull(userResult);
        assertEquals(user1, userResult);

    }

    /**
     * Testing userDao.delete()
     * @throws DaoException
     */
    @Test
    public void testDelete() throws DaoException {
        User userResult = user2;
        try {
            session.beginTransaction();
            userDao.delete(user2);
            userResult = userDao.get(user2.getId());
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNull(userResult);

    }

    /**
     * Testing userDao.getUserByLoginAndPassword()
     * @throws DaoException
     */
    @Test
    public void testGetUserByLoginAndPassword() throws DaoException {
        User userResult = null;
        userDao = new UserDaoImpl(User.class);
        try {
            session.beginTransaction();
            userResult = userDao.getUserByLoginAndPassword(user1.getLogin(), user1.getPassword());
            session.getTransaction().commit();
        } catch (DaoException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNotNull(userResult);
        assertEquals(user1, userResult);
    }
}