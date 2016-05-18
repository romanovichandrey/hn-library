package by.romanovich.it.service.service;

import by.romanovich.it.dao.UserDao;
import by.romanovich.it.dao.UserDaoImpl;
import by.romanovich.it.dao.exeptions.DaoException;
import by.romanovich.it.pojos.User;
import by.romanovich.it.service.exeptions.ServiceErrorCode;
import by.romanovich.it.service.exeptions.ServiceExeption;
import org.apache.log4j.Logger;
import org.hibernate.Query;

import java.util.List;

/**
 * Implementing UserService interface.
 * Processing business-logic for work with User.
 * @see by.romanovich.it.service.service.UserService
 * @author Romanovich Angrei
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    private static Logger log = Logger.getLogger(UserServiceImpl.class);

    private static UserServiceImpl userService = null;

    private static UserDao userDao = null;

    private UserServiceImpl() {
        userDao = new UserDaoImpl(User.class);
    }

    public synchronized static UserServiceImpl getUserService() {
        if(userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    @Override
    public Boolean updateUser(User user) throws ServiceExeption {
        try {
            userDao.getHibernateSession().beginTransaction();
            userDao.update(user);
            log.info("Updating user:" + user);
            userDao.getHibernateSession().getTransaction().commit();
            return true;
        } catch (DaoException e) {
            userDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_003);
        }
    }

    @Override
    public User getUserById(Long id) throws ServiceExeption {
        User user = null;
        try {
            userDao.getHibernateSession().beginTransaction();
            user = userDao.get(id);
            userDao.getHibernateSession().getTransaction().commit();
            log.info("Getting user:" + user);
        } catch (DaoException e) {
            userDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_000);
        }
        return user;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws ServiceExeption {
        User user = null;
        try {
            userDao.getHibernateSession().beginTransaction();
            user = userDao.getUserByLoginAndPassword(login, password);
            userDao.getHibernateSession().getTransaction().commit();
            log.info("Getting user:" + user);
        } catch (DaoException e) {
            userDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_004);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws ServiceExeption {
        List<User> users = null;
        try {
            userDao.getHibernateSession().beginTransaction();
            users = userDao.getAll();
            userDao.getHibernateSession().getTransaction().commit();
            for(User user : users)
                log.info("Getting all users:" + user);
            return users;
        } catch (DaoException e) {
            userDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_005);
        }
    }

    @Override
    public Boolean deleteUser(User user) throws ServiceExeption {
        try {
            userDao.getHibernateSession().beginTransaction();
            userDao.delete(user);
            userDao.getHibernateSession().getTransaction().commit();
            log.info("Deleting user:" + user);
            return true;
        } catch (DaoException e) {
            userDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_002);
        }
    }

    @Override
    public Boolean saveUser(User user) throws ServiceExeption {
        try {
            userDao.getHibernateSession().beginTransaction();
            userDao.add(user);
            userDao.getHibernateSession().getTransaction().commit();
            log.info("Adding user:" + user);
            return true;
        } catch (DaoException e) {
            userDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_001);
        }
    }

    @Override
    public List<User> findBooks(Integer start, Integer end) throws ServiceExeption {
        try {
            userDao.getHibernateSession().beginTransaction();
            Query query = userDao.getQuery("from User");
            query.setFirstResult(start);
            query.setMaxResults(end);
            List<User> userList = query.list();
            userDao.getHibernateSession().getTransaction().commit();
            return userList;
        } catch (DaoException e) {
            userDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_018);
        }
    }

    @Override
    public Long getRowCountBooks() throws ServiceExeption {
        try {
            userDao.getHibernateSession().beginTransaction();
            Query query = userDao.getQuery("select count(distinct id) from User");
            Long countResult = (Long) query.uniqueResult();
            userDao.getHibernateSession().getTransaction().commit();
            return countResult;
        } catch (DaoException e) {
            userDao.getHibernateSession().getTransaction().rollback();
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_019);
        }
    }
}
