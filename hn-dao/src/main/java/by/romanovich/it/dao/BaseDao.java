package by.romanovich.it.dao;

import by.romanovich.it.dao.exeptions.DaoErrorCode;
import by.romanovich.it.dao.exeptions.DaoExeption;
import by.romanovich.it.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.Serializable;

/**
 * Implementing Dao interface. Creating Dao generic
 * @see by.romanovich.it.dao.Dao
 * @author Romanovich Andrey
 * @version 1.0
 */
public class BaseDao<T, PK extends Serializable> implements Dao<T, PK> {

    private static Logger log = Logger.getLogger(BaseDao.class);

    private Class<T> type;

    public BaseDao(Class<T> type) {
        this.type = type;
    }

    protected Session getSession() {
        return HibernateUtil.getUtil().getSession();
    }

    @Override
    public T get(PK id) throws DaoExeption {
        try {
            log.info("Getting object with id" + id);
            T entity = (T) getSession().get(type, id);
            return entity;
        } catch (HibernateException e) {
            throw new DaoExeption(e, DaoErrorCode.NC_DAO_000);
        }
    }

    @Override
    public PK add(T object) throws DaoExeption {
        try {
            PK id = (PK) getSession().save(object);
            log.info("Added object with id" + id);
            return id;
        } catch (HibernateException e) {
            throw new DaoExeption(e, DaoErrorCode.NC_DAO_002);
        }
    }

    @Override
    public void update(T object) throws DaoExeption {
        try {
            log.info("Updating object");
            getSession().saveOrUpdate(object);
        } catch (HibernateException e) {
            throw new DaoExeption(e, DaoErrorCode.NC_DAO_003);
        }
    }

    @Override
    public void delete(T object) throws DaoExeption {
        try {
            log.info("Deleting object");
            getSession().delete(object);
        } catch (HibernateException e) {
            throw new DaoExeption(e, DaoErrorCode.NC_DAO_004);
        }
    }

}
