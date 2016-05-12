package by.romanovich.it.dao;

import by.romanovich.it.dao.exeptions.DaoExeption;

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
     * @throws DaoExeption
     */
    T get(PK id) throws DaoExeption;

    /**
     * Getting all T entity.
     * @return
     * @throws DaoExeption
     */
    //List<T> getAll() throws DaoExeption;

    /**
     * Adding T entity.
     * @param object T entity.
     * @return id T entity
     * @throws DaoExeption
     */
    PK add(T object) throws DaoExeption;

    /**
     * Updating T entity.
     * @param object T entity.
     * @throws DaoExeption
     */
    void update(T object) throws DaoExeption;

    /**
     * Deleting T entity.
     * @param object T entity.
     * @throws DaoExeption
     */
    void delete(T object) throws DaoExeption;

}
