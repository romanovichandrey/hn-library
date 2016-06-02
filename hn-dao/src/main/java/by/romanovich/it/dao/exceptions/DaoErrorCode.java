package by.romanovich.it.dao.exceptions;

/**
 * Enuming messqge error on dao layer
 * @author Romanovich Andrei
 * @version 1.0
 */
public enum  DaoErrorCode {

    HN_DAO_000("Cannot get by id"),
    HN_DAO_001("Cannot get list of object"),
    HN_DAO_002("Cannot add object"),
    HN_DAO_003("Cannot update object"),
    HN_DAO_004("Cannot delete object"),
    HN_DAO_005("Cannot get by login and password"),
    HN_DAO_006("Cannot create hql");


    private final String value;

    private DaoErrorCode(String s) {
        value = s;
    }

    public boolean equalsValue(String value2) {
        return (value2 != null) && value.equals(value2);
    }

    public String toString() {
        return value;
    }
}
