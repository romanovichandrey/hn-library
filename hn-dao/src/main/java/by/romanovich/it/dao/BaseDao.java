package by.romanovich.it.dao;

import by.romanovich.it.dao.exeptions.DaoExeption;
import by.romanovich.it.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    private Transaction transaction = null;

    public BaseDao(Class<T> type) {
        this.type = type;
    }

    public BaseDao() {
    }

    @Override
    public void saveOrOpdate(T t) throws DaoExeption {
        try {
            log.info("Save or Update Object");
            Session session = HibernateUtil.getUtil().getSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(t);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Error save or update for Dao" + e);
            transaction.rollback();
            throw new DaoExeption(e);
        }

    }

    @Override
    public T get(PK id) throws DaoExeption {
        try {
            Session session = HibernateUtil.getUtil().getSession();
            transaction = session.beginTransaction();
            log.info("Getting object with id: " + id);
            T entity = (T) session.get(type, id);
            transaction.commit();
            return entity;
        } catch (HibernateException e) {
            log.error("Get object failed." + e);
            transaction.rollback();
            throw new DaoExeption(e);
        }
    }

    @Override
    public T load(PK id) throws DaoExeption {
        try {
            Session session = HibernateUtil.getUtil().getSession();
            transaction = session.beginTransaction();
            log.info("Load object" + id);
            T entity = (T) session.load(type, id);
            transaction.commit();
            return entity;
        }catch (HibernateException e) {
            log.error("Error load object  failed" + e);
            transaction.rollback();
            throw new DaoExeption(e);
        }
    }

    @Override
    public void delete(T t) throws DaoExeption {
        try {
            Session session = HibernateUtil.getUtil().getSession();
            transaction = session.beginTransaction();
            log.info("Delete object");
            session.delete(t);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Delete object failed" + e);
            transaction.rollback();
            throw new DaoExeption(e);
        }

    }
}
