package by.romanovich.it.dao.userdao;

import by.romanovich.it.dao.BaseDao;
import by.romanovich.it.pojos.Adress;

public class AdressDao extends BaseDao<Adress, Long> {

    public AdressDao(Class<Adress> type) {
        super(type);
    }
}
