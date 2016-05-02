package by.romanovich.it.dao;

import by.romanovich.it.dao.exeptions.DaoExeption;
import java.io.Serializable;

/**
 * This is interfafe for BaseDao class.
 * @see by.romanovich.it.dao.BaseDao
 * @param <T> This option is for pojos classes
 * @param <PK> This option is for primary key pojos classes
 * @author Romanovich Andrey
 * @version 1.0
 */
public interface Dao<T, PK extends Serializable> {

    /**
     * This method creates an object from the database
     * or update it if it does not exist
     * @param t Entity object
     * @throws DaoExeption
     */
    void saveOrOpdate(T t) throws DaoExeption;

    /**
     * This method loads the object from database
     * @param id Entity identifier
     * @return Return object is for pojos classes
     * @throws DaoExeption
     */
    T get(PK id) throws DaoExeption;

    /**
     * This method loads the object from database
     * @param id Entity identifier
     * @return Return object is for pojos classes
     * @throws DaoExeption
     */
    T load(PK id) throws DaoExeption;

    /**
     * This method delete object from database
     * @param t Entity object
     * @throws DaoExeption
     */
    void delete(T t) throws DaoExeption;

}
