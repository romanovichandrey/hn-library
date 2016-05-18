package by.romanovich.it.dao;

import by.romanovich.it.dao.exeptions.DaoErrorCode;
import by.romanovich.it.dao.exeptions.DaoException;
import by.romanovich.it.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

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

    protected Session getSession(){
        return HibernateUtil.getUtil().getSession();
    }

    @Override
    public Session getHibernateSession() {
        return getSession();
    }

    @Override
    public T get(PK id) throws DaoException {
        try {
            log.info("Getting object with id" + id);
            T entity = (T) getSession().get(type, id);
            return entity;
        } catch (HibernateException e) {
            throw new DaoException(e, DaoErrorCode.HN_DAO_000);
        }
    }

    @Override
    public List<T> getAll() throws DaoException {
        try {
            log.info("Getting list of object");
            List<T> list = getSession().createCriteria(type).list();
            return list;
        } catch (HibernateException e) {
            throw new DaoException(e, DaoErrorCode.HN_DAO_001);
        }
    }

    @Override
    public PK add(T object) throws DaoException {
        try {
            PK id = (PK) getSession().save(object);
            log.info("Added object with id" + id);
            return id;
        } catch (HibernateException e) {
            throw new DaoException(e, DaoErrorCode.HN_DAO_002);
        }
    }

    @Override
    public void update(T object) throws DaoException {
        try {
            log.info("Updating object");
            getSession().saveOrUpdate(object);
        } catch (HibernateException e) {
            throw new DaoException(e, DaoErrorCode.HN_DAO_003);
        }
    }

    @Override
    public void delete(T object) throws DaoException {
        try {
            log.info("Deleting object");
            getSession().delete(object);
        } catch (HibernateException e) {
            throw new DaoException(e, DaoErrorCode.HN_DAO_004);
        }
    }

    @Override
    public Query getQuery(String hql) throws DaoException {
        try {
            log.info("Getting hql query");
            Query query = getSession().createQuery(hql);
            return query;
        } catch (HibernateException e) {
            throw new DaoException(e, DaoErrorCode.HN_DAO_006);
        }

    }
}
