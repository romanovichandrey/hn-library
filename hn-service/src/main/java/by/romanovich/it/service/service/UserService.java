package by.romanovich.it.service.service;

import by.romanovich.it.pojos.User;
import by.romanovich.it.service.exeptions.ServiceExeption;

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
     * @throws ServiceExeption
     */
    Boolean updateUser(User user) throws ServiceExeption;

    /**
     * This method getting user by id.
     * @param id User id
     * @return User
     * @throws ServiceExeption
     */
    User getUserById(Long id) throws ServiceExeption;

    /**
     * This method getting user by login and password
     * @param login User login
     * @param password User password
     * @return User
     * @throws ServiceExeption
     */
    User getUserByLoginAndPassword(String login, String password) throws ServiceExeption;

    /**
     * This method getting all users
     * @return list users
     * @throws ServiceExeption
     */
    List<User> getAllUsers() throws ServiceExeption;

    /**
     * This method delete user by id.
     * @return true if user successfully is delete.
     * @throws ServiceExeption
     */
    Boolean deleteUser(User user) throws ServiceExeption;

    /**
     * This method saving new user.
     * @param user
     * @return true if user successfully is save.
     * @throws ServiceExeption
     */
    Boolean saveUser(User user) throws ServiceExeption;

}
