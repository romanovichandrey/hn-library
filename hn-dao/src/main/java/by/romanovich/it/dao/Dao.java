package by.romanovich.it.dao;

import by.romanovich.it.dao.exeptions.DaoException;

import java.io.Serializable;

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
     * @return
     * @throws by.romanovich.it.dao.exeptions.DaoException
     */
    //List<T> getAll() throws DaoExeption;

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

}
