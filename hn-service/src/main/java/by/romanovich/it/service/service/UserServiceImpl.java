package by.romanovich.it.service.service;

import by.romanovich.it.dao.BaseDao;
import by.romanovich.it.dao.UserDao;
import by.romanovich.it.dao.UserDaoImpl;
import by.romanovich.it.dao.exeptions.DaoException;
import by.romanovich.it.pojos.User;
import by.romanovich.it.service.exeptions.ServiceErrorCode;
import by.romanovich.it.service.exeptions.ServiceExeption;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Implementing UserService innterface.
 * Processing business-logic for work with User.
 * @see by.romanovich.it.service.service.UserService
 * @author Romanovich Angrei
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    private static Logger log = Logger.getLogger(UserServiceImpl.class);

    private static UserServiceImpl userService = null;

    private static UserDao userDaoImpl = null;

    private BaseDao<User, Long> userDao = null;

    private UserServiceImpl() {
        userDao = new UserDaoImpl(User.class);
        userDaoImpl = new UserDaoImpl(User.class);
    }

    public static UserServiceImpl getUserService() {
        if(userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    @Override
    public Boolean updateUser(User user) throws ServiceExeption {
        try {
            userDao.update(user);
            log.info("Updating user:" + user);
            return true;
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_003);
        }
    }

    @Override
    public User getUserById(Long id) throws ServiceExeption {
        User user = null;
        try {
            user = userDao.get(id);
            log.info("Getting user:" + user);
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_000);
        }
        return user;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws ServiceExeption {
        User user = null;
        try {
            user = userDaoImpl.getUserByLoginAndPassword(login, password);
            log.info("Getting user:" + user);
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_004);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws ServiceExeption {
        List<User> users = null;
        try {
            users = userDao.getAll();
            for(User user : users)
                log.info("Getting all users:" + user);
            return users;
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_005);
        }
    }

    @Override
    public Boolean deleteUserById(User user) throws ServiceExeption {
        try {
            userDao.delete(user);
            log.info("Deleting user:" + user);
            return true;
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_002);
        }
    }

    @Override
    public Boolean saveUser(User user) throws ServiceExeption {
        try {
            userDao.add(user);
            log.info("Adding user:" + user);
            return true;
        } catch (DaoException e) {
            throw new ServiceExeption(e, ServiceErrorCode.HN_SERV_001);
        }
    }
}
