package by.romanovich.it.dao.userdao;

import by.romanovich.it.dao.BaseDao;
import by.romanovich.it.pojos.User;
import by.romanovich.it.util.HibernateUtil;
import org.apache.log4j.Logger;

public class UserDao extends BaseDao {

    private static Logger log = Logger.getLogger(BaseDao.class);

    private Class<User> userType;

    private HibernateUtil hibernateUtil = HibernateUtil.getUtil();

    public UserDao(Class type, Class<User> userType) {
        super(type);
        this.userType = userType;
    }
}
