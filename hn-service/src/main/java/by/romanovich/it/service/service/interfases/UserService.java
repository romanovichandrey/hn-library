package by.romanovich.it.service.service.interfases;

import by.romanovich.it.pojos.User;
import by.romanovich.it.service.exeptions.ServiceException;

import java.util.List;

/**
 * Interface for UserServiceImpl class.
 * @see by.romanovich.it.service.service.UserServiceImpl
 * @author Romanovich Andrei
 * @version 1.0
 */
public interface UserService {

    /**
     * This method updating user.
     * @param user
     * @return true if user successfully is updated.
     * @throws by.romanovich.it.service.exeptions.ServiceException
     */
    Boolean updateUser(User user) throws ServiceException;

    /**
     * This method getting user by id.
     * @param id User id
     * @return User
     * @throws by.romanovich.it.service.exeptions.ServiceException
     */
    User getUserById(Long id) throws ServiceException;

    /**
     * This method getting user by login and password
     * @param login User login
     * @param password User password
     * @return User
     * @throws by.romanovich.it.service.exeptions.ServiceException
     */
    User getUserByLoginAndPassword(String login, String password) throws ServiceException;

    /**
     * This method getting all users
     * @return list users
     * @throws by.romanovich.it.service.exeptions.ServiceException
     */
    List<User> getAllUsers() throws ServiceException;

    /**
     * This method delete user by id.
     * @return true if user successfully is delete.
     * @throws by.romanovich.it.service.exeptions.ServiceException
     */
    Boolean deleteUser(User user) throws ServiceException;

    /**
     * This method saving new user.
     * @param user
     * @return true if user successfully is save.
     * @throws by.romanovich.it.service.exeptions.ServiceException
     */
    Boolean saveUser(User user) throws ServiceException;

    /**
     * This method getting users by start and end positions.
     * @param start Integer start
     * @param end Integer end
     * @return list users
     * @throws by.romanovich.it.service.exeptions.ServiceException
     */
    List<User> findBooks(Integer start, Integer end) throws ServiceException;

    /**
     * This method getting count users.
     * @return Integer
     * @throws by.romanovich.it.service.exeptions.ServiceException
     */
    Long getRowCountBooks() throws ServiceException;

}
