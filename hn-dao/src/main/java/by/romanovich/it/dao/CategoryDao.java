package by.romanovich.it.dao;

import by.romanovich.it.pojos.Category;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Extends BaseDao class. Creating BaseDao generic
 * @see BaseDao
 * @author Romanovich Andrey
 * @version 1.0
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class CategoryDao extends BaseDao<Category, Long> {

    private static Logger log = Logger.getLogger(CategoryDao.class);

}
