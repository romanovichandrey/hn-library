package by.romanovich.it.dao;

import by.romanovich.it.pojos.Autor;

/**
 * Extends BaseDao class. Creating BaseDao generic
 * @see by.romanovich.it.dao.BaseDao
 * @author Romanovich Andrey
 * @version 1.0
 */
public class AutorDao extends BaseDao<Autor, Long> {

    public AutorDao(Class<Autor> type) {
        super(type);
    }
}
