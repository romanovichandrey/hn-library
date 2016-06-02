package by.romanovich.it.dao;

import by.romanovich.it.dao.exceptions.DaoErrorCode;
import by.romanovich.it.dao.exceptions.DaoException;
import by.romanovich.it.dao.interfaces.UserDao;
import by.romanovich.it.pojos.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Extends BaseDao class. Creating BaseDao generic
 * @see BaseDao
 * @author Romanovich Andrey
 * @version 1.0
 */
@Repository("userDao")
@Transactional(propagation = Propagation.MANDATORY)
public class UserDaoImpl extends BaseDao<User, Long> implements UserDao {

    private static Logger log = Logger.getLogger(UserDaoImpl.class);

    @Override
    public User getUserByLogin(String login) throws DaoException {
        User user = null;
        String hql = "FROM User u WHERE u.login=:login";
        try {
            Query query = getSession().createQuery(hql);
            query.setParameter("login", login);
            log.info("Getting user by login");
            user = (User)query.uniqueResult();
            return user;
        } catch (HibernateException e) {
            throw new DaoException(e, DaoErrorCode.HN_DAO_005);
        }
    }


}
