package by.romanovich.it.dao.userdao;

import by.romanovich.it.dao.BaseDao;
import by.romanovich.it.pojos.Adress;
import by.romanovich.it.util.HibernateUtil;
import org.apache.log4j.Logger;

public class AdressDao extends BaseDao {

    private static Logger log = Logger.getLogger(BaseDao.class);

    private Class<Adress> adressType;

    private HibernateUtil hibernateUtil = HibernateUtil.getUtil();

    public AdressDao(Class type, Class<Adress> adressType) {
        super(type);
        this.adressType = adressType;
    }
}
