package by.romanovich.it.dao.userdao;

import by.romanovich.it.dao.BaseDao;
import by.romanovich.it.pojos.User;

public class UserDao extends BaseDao<User, Long> {

    public UserDao(Class<User> type) {
        super(type);
    }
}
