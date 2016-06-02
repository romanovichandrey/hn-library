package by.romanovich.it.service.service;

import by.romanovich.it.dao.exceptions.DaoException;
import by.romanovich.it.dao.interfaces.UserDao;
import by.romanovich.it.pojos.Adress;
import by.romanovich.it.pojos.User;
import by.romanovich.it.service.exceptions.ServiceException;
import by.romanovich.it.service.service.interfases.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Testing interface UserServiceImpl.
 *
 * @author Romanovich Andrei
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest extends Assert {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    private Adress adress1 = null;

    private Adress adress2 = null;

    private User user1 = null;

    private User user2 = null;

    private List<User> userList;

    /**
     * Initialize custom parameter for tests.
     */
    @Before
    public void initUserDao() {
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

        userList = Arrays.asList(user1, user2);
    }

    /**
     * Testing userService.updateUser()
     */
    @Test
    public void testUpdateUser(){
        user1.setLastname(user2.getLastname());
        user1.setAdress(adress2);
        try {
            userService.updateUser(user1);
            verify(userDao).update(user1);
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }

    /**
     * Testing userService.getUserById()
     */
    @Test
    public void testGetUserById() {
        try {
            when(userDao.get(user1.getId())).thenReturn(user1);

            assertEquals(user1, userService.getUserById(user1.getId()));
        }catch (DaoException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testing userService.getUserByUserName()
     */
    @Test
    public void testGetUserByLogin() {
        try {
            when(userDao.getUserByLogin(user1.getLogin())).thenReturn(user1);

            assertEquals(user1, userService.getUserByUserName(user1.getLogin()));
        }catch (DaoException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testing userService.getAllUsers()
     */
    @Test
    public void testGetAllUsers() {
        try{
            when(userDao.getAll()).thenReturn(userList);

            assertNotNull(userService.getAllUsers());
            assertEquals(userList, userService.getAllUsers());
        }catch (ServiceException e){
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    /**
     * Testing userService.deleteUser()
     */
    @Test
    public void testDeleteUser() throws Exception {
        try {
            doNothing().when(userDao).delete(user2);

            assertTrue(userService.deleteUser(user2));
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testing userService.saveUser()
     */
    @Test
    public void testSaveUser() throws Exception {
        try {
            userService.saveUser(user2);

            verify(userDao).add(user2);
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

}