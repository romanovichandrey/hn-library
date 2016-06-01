package by.romanovich.it.dao.interfaces;

import by.romanovich.it.dao.exeptions.DaoException;
import by.romanovich.it.pojos.User;

/**
 * Interface for UserDaoImpl
 * @see by.romanovich.it.dao.UserDaoImpl
 * @author Romanovich Andrei
 * @version 1.0
 */
public interface UserDao extends Dao<User, Long> {

    /**
     * Getting User by login and password
     * @param login User login
     * @param password User password
     * @return user
     * @throws DaoException
     */
    User getUserByLoginAndPassword(String login, String password) throws DaoException;

}
