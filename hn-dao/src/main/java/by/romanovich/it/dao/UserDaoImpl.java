package by.romanovich.it.dao;

import by.romanovich.it.dao.exeptions.DaoErrorCode;
import by.romanovich.it.dao.exeptions.DaoException;
import by.romanovich.it.pojos.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 * Extends BaseDao class. Creating BaseDao generic
 * @see by.romanovich.it.dao.BaseDao
 * @author Romanovich Andrey
 * @version 1.0
 */
public class UserDaoImpl extends BaseDao<User, Long> implements UserDao{

    private static Logger log = Logger.getLogger(UserDaoImpl.class);



    public UserDaoImpl(Class<User> type) {
        super(type);
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws DaoException {
        User user = null;
        String hql = "FROM User u WHERE u.login=:login AND u.password=:password";
        try {
            Query query = getSession().createQuery(hql);
            query.setParameter("login", login);
            query.setParameter("password", password);
            log.info("Getting user by login and password");
            user = (User)query.uniqueResult();
            return user;
        } catch (HibernateException e) {
            throw new DaoException(e, DaoErrorCode.HN_DAO_005);
        }
    }


}
