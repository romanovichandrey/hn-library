package by.romanovich.it.dao;

import by.romanovich.it.dao.exceptions.DaoErrorCode;
import by.romanovich.it.dao.exceptions.DaoException;
import by.romanovich.it.dao.interfaces.Dao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Implementing Dao interface. Creating Dao generic
 * @see by.romanovich.it.dao.interfaces.Dao
 * @author Romanovich Andrey
 * @version 1.0
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public abstract class BaseDao<T, PK extends Serializable> implements Dao<T, PK> {

    private static Logger log = Logger.getLogger(BaseDao.class);

    protected Class<? extends T> type;

    @Autowired
    private SessionFactory sessionFactory;

    public BaseDao() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
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
