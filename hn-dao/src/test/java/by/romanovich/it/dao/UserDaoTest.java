package by.romanovich.it.dao;

import by.romanovich.it.dao.BaseDao;
import by.romanovich.it.dao.UserDao;
import by.romanovich.it.dao.exeptions.DaoExeption;
import by.romanovich.it.pojos.Adress;
import by.romanovich.it.pojos.User;
import by.romanovich.it.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Testing class UserDao.
 * @see by.romanovich.it.dao.UserDao
 * @author Romanovich Andrei
 * @version 1.0
 */
public class UserDaoTest extends Assert{

    private static BaseDao<User, Long> userDao = null;

    private static Adress adress1 = null;

    private static Adress adress2 = null;

    private static User user1 = null;

    private static User user2 = null;

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
    }

    /**
     * Clear custom parameter.
     */
   @AfterClass
    public static void clearHibernateUtil() {
       hibernateUtil.sessionClose();
       hibernateUtil = null;
       userDao = null;
    }

    /**
     * Testing userDao.add()
     * @throws DaoExeption
     */
   @Test
    public void testAdd() throws DaoExeption {
       Long userIdResult = null;
       userDao = new UserDao(User.class);
       try {
           session.beginTransaction();
           user1.setAdress(adress1);
           adress1.setUser(user1);
           user2.setAdress(adress2);
           adress2.setUser(user2);
           userIdResult = userDao.add(user1);
           userDao.add(user2);
           session.getTransaction().commit();
       } catch (DaoExeption e) {
           e.printStackTrace();
           session.getTransaction().rollback();
       }
       assertNotNull(userIdResult);
    }

    /**
     * Testing userDao.get()
     * @throws DaoExeption
     */
    @Test
    public void testGet() throws DaoExeption {
        User userResult = null;
        try {
            session.beginTransaction();
            userResult = userDao.get(user1.getId());
            session.getTransaction().commit();
        } catch (DaoExeption e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNotNull(userResult);
        assertEquals(user1, userResult);

    }

    /**
     * Testing userDao.update()
     * @throws DaoExeption
     */
    @Test
    public void testUpdate() throws DaoExeption {
        user1.setPassword("test");
        User userResult = null;
        try {
            session.beginTransaction();
            userDao.update(user1);
            userResult = userDao.get(user1.getId());
            session.getTransaction().commit();
        } catch (DaoExeption e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNotNull(userResult);
        assertEquals(user1, userResult);

    }

    /**
     * Testing userDao.delete()
     * @throws DaoExeption
     */
    @Test
    public void testDelete() throws DaoExeption {
        User userResult = user2;
        try {
            session.beginTransaction();
            userDao.delete(user2);
            userResult = userDao.get(user2.getId());
            session.getTransaction().commit();
        } catch (DaoExeption e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        assertNull(userResult);

    }
}