package by.romanovich.it.service.service;

import by.romanovich.it.pojos.Adress;
import by.romanovich.it.pojos.User;
import by.romanovich.it.service.exeptions.ServiceExeption;
import by.romanovich.it.util.HibernateUtil;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Testing interface UserServiceImpl.
 * @author Romanovich Andrei
 * @version 1.0
 */
public class UserServiceImplTest extends Assert {

        private static UserService userService = null;

        private static Adress adress1 = null;

        private static Adress adress2 = null;

        private static User user1 = null;

        private static User user2 = null;

        private static HibernateUtil hibernateUtil = null;

        /**
         * Initialize custom parameter for tests.
         */
        @BeforeClass
        public static void initUserDao() {
            hibernateUtil = HibernateUtil.getUtil();
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
            userService = null;
            user1 = null;
            user2 = null;
            adress1 = null;
            adress2 = null;
        }

        /**
         * Testing userService.saveUser and userService.getAllUsers()
         * @throws ServiceExeption
         */
        @Test
        public void testSaveUser() throws ServiceExeption {
            Boolean userSaveResult1 = false;
            Boolean userSaveResult2 = false;
            List<User> users = null;
            User userResult = user2;
            Boolean userDeleteResult = false;
            userService = UserServiceImpl.getUserService();
            try {
                user1.setAdress(adress1);
                adress1.setUser(user1);
                user2.setAdress(adress2);
                adress2.setUser(user2);
                userSaveResult1 = userService.saveUser(user1);
                userSaveResult2 = userService.saveUser(user2);
                users = userService.getAllUsers();
                userDeleteResult = userService.deleteUser(user2);
                userResult = userService.getUserById(user2.getId());
            } catch (ServiceExeption e) {
                e.printStackTrace();
            }
            assertTrue(userSaveResult1);
            assertTrue(userSaveResult2);
            assertNotNull(users);
            List<User> userTest = Arrays.asList(user1, user2);
            assertEquals(userTest.size(), users.size());
            assertTrue(userDeleteResult);
            assertNull(userResult);
        }

    /**
     * Testing userService.getUserService()
     */
    @Test
    public void testGetUserService() {
        userService = UserServiceImpl.getUserService();
        assertNotNull(userService);
        userService = null;
        assertNull(userService);
    }


    /**
     * Testing userService.updateUser()
     * @throws ServiceExeption
     */
    @Test
    public void testUpdateUser() throws ServiceExeption {
        Boolean userUpdateResult = false;
        user1.setLastname("Ivanov");
        adress1.setCity("Lida");
        user1.setAdress(adress1);
        adress1.setUser(user1);
        userService = UserServiceImpl.getUserService();
        try {
            userUpdateResult = userService.updateUser(user1);
        } catch (ServiceExeption e) {
            e.printStackTrace();
        }
        assertTrue(userUpdateResult);

    }

    /**
     * Testing userService.getUserByLoginAndPassword
     * @throws by.romanovich.it.service.exeptions.ServiceExeption
     */
    @Test
    public void testGetUserByLoginAndPassword() throws ServiceExeption{
        String login = user1.getLogin();
        String password = user1.getPassword();
        User userResult = null;
        userService = UserServiceImpl.getUserService();
        try {
            userResult = userService.getUserByLoginAndPassword(login, password);
        } catch (ServiceExeption e) {
            e.printStackTrace();
        }
        assertNotNull(userResult);
        assertEquals(user1, userResult);
    }

    /**
     * Testing userService.getUserById()
     * @throws ServiceExeption
     */
    @Test
    public void testGetUserById() throws ServiceExeption {
        User userIdResult = null;
        userService = UserServiceImpl.getUserService();
        try {
            userIdResult = userService.getUserById(user1.getId());
        } catch (ServiceExeption e) {
            e.printStackTrace();
        }
        assertNotNull(userIdResult);
        assertEquals(user1, userIdResult);

    }



}