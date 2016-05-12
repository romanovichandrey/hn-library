package by.romanovich.it.util;

import org.hibernate.cfg.DefaultNamingStrategy;

/**
 * This is class NamingStrategy
 * @see org.hibernate.cfg.DefaultNamingStrategy
 * @author Romanovich Andrei
 * @version 1.0
 */
public class CustomNamingStrategy extends DefaultNamingStrategy {

    @Override
    public String classToTableName(String className) {
        return "T_" + super.classToTableName(className).toUpperCase();
    }

    @Override
    public String propertyToColumnName(String propertyName) {
        return "F_" + super.propertyToColumnName(propertyName);
    }

    @Override
    public String tableName(String tableName) {
        return tableName;
    }

    @Override
    public String columnName(String columnName) {
        return columnName;
    }
}
