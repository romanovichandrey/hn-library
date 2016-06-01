package by.romanovich.it.service.service;

import by.romanovich.it.dao.exeptions.DaoException;
import by.romanovich.it.dao.interfaces.UserDao;
import by.romanovich.it.pojos.User;
import by.romanovich.it.service.exeptions.ServiceErrorCode;
import by.romanovich.it.service.exeptions.ServiceException;
import by.romanovich.it.service.service.interfases.UserService;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementing UserService interface.
 * Processing business-logic for work with User.
 * @see by.romanovich.it.service.service.interfases.UserService
 * @author Romanovich Angrei
 * @version 1.0
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private static Logger log = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public Boolean updateUser(User user) throws ServiceException {
        try {
            userDao.update(user);
            log.info("Updating user:" + user);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_003);
        }
    }

    @Override
    public User getUserById(Long id) throws ServiceException {
        User user = null;
        try {
            user = userDao.get(id);
            log.info("Getting user:" + user);
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_000);
        }
        return user;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws ServiceException {
        User user = null;
        try {
            user = userDao.getUserByLoginAndPassword(login, password);
            log.info("Getting user:" + user);
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_004);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        List<User> users = null;
        try {
            users = userDao.getAll();
            for(User user : users)
                log.info("Getting all users:" + user);
            return users;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_005);
        }
    }

    @Override
    public Boolean deleteUser(User user) throws ServiceException {
        try {
            userDao.delete(user);
            log.info("Deleting user:" + user);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_002);
        }
    }

    @Override
    public Boolean saveUser(User user) throws ServiceException {
        try {
            userDao.add(user);
            log.info("Adding user:" + user);
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_001);
        }
    }

    @Override
    public List<User> findBooks(Integer start, Integer end) throws ServiceException {
        try {
            Query query = userDao.getQuery("from User");
            query.setFirstResult(start);
            query.setMaxResults(end);
            List<User> userList = query.list();
            return userList;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_018);
        }
    }

    @Override
    public Long getRowCountBooks() throws ServiceException {
        try {
            Query query = userDao.getQuery("select count(distinct id) from User");
            Long countResult = (Long) query.uniqueResult();
            return countResult;
        } catch (DaoException e) {
            throw new ServiceException(e, ServiceErrorCode.HN_SERV_019);
        }
    }
}
