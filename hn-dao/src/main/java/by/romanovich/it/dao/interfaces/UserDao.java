package by.romanovich.it.dao.interfaces;

import by.romanovich.it.dao.exceptions.DaoException;
import by.romanovich.it.pojos.User;

/**
 * Interface for UserDaoImpl
 * @see by.romanovich.it.dao.UserDaoImpl
 * @author Romanovich Andrei
 * @version 1.0
 */
public interface UserDao extends Dao<User, Long> {

    /**
     * Getting User by login
     * @param login User login
     * @return user
     * @throws DaoException
     */
    User getUserByLogin(String login) throws DaoException;


}
