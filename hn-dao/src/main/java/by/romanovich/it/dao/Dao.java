package by.romanovich.it.dao;

import by.romanovich.it.dao.exeptions.DaoException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * This is interfafe for BaseDao class.
 * @see by.romanovich.it.dao.BaseDao
 * @param <T> This option is for pojos classes
 * @author Romanovich Andrey
 * @version 1.0
 */
public interface Dao<T, PK extends Serializable> {

    /**
     * Getting T entity by id.
     * @param id
     * @return entity.
     * @throws by.romanovich.it.dao.exeptions.DaoException
     */
    T get(PK id) throws DaoException;

    /**
     * Getting all T entity.
     * @return List entity
     * @throws by.romanovich.it.dao.exeptions.DaoException
     */
    List<T> getAll() throws DaoException;

    /**
     * Adding T entity.
     * @param object T entity.
     * @return id T entity
     * @throws by.romanovich.it.dao.exeptions.DaoException
     */
    PK add(T object) throws DaoException;

    /**
     * Updating T entity.
     * @param object T entity.
     * @throws by.romanovich.it.dao.exeptions.DaoException
     */
    void update(T object) throws DaoException;

    /**
     * Deleting T entity.
     * @param object T entity.
     * @throws by.romanovich.it.dao.exeptions.DaoException
     */
    void delete(T object) throws DaoException;

    /**
     * Getting HibernateSession.
     * @return session Session session
     */
    Session getHibernateSession();

    /**
     * Getting hql query.
     * @param hql string param.
     * @return query.
     * @throws DaoException
     */
    Query getQuery(String hql) throws DaoException;

}
