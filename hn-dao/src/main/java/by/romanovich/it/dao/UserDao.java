package by.romanovich.it.dao;

import by.romanovich.it.dao.BaseDao;
import by.romanovich.it.pojos.User;

/**
 * Extends BaseDao class. Creating BaseDao generic
 * @see by.romanovich.it.dao.BaseDao
 * @author Romanovich Andrey
 * @version 1.0
 */
public class UserDao extends BaseDao<User, Long> {

    public UserDao(Class<User> type) {
        super(type);
    }
}
