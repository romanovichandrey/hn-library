package by.romanovich.it.dao.userdao;

import by.romanovich.it.dao.BaseDao;
import by.romanovich.it.pojos.UserLogin;
import by.romanovich.it.util.HibernateUtil;
import org.apache.log4j.Logger;

public class UserLoginDao extends BaseDao {

    private static Logger log = Logger.getLogger(BaseDao.class);

    private Class<UserLogin> userLoginType;

    private HibernateUtil hibernateUtil = HibernateUtil.getUtil();

    public UserLoginDao(Class type, Class<UserLogin> userLoginType) {
        super(type);
        this.userLoginType = userLoginType;
    }
}
