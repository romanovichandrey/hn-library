package by.romanovich.it.dao;

import by.romanovich.it.dao.exeptions.DaoExeption;

import java.io.Serializable;

public interface Dao<T> {

    void saveOrOpdate(T t) throws DaoExeption;

    T get(Serializable id) throws DaoExeption;

    T load(Serializable id) throws DaoExeption;

    void delete(T t) throws DaoExeption;

}
