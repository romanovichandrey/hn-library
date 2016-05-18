package by.romanovich.it.service.exeptions;

/**
 * Enuming messqge error on service layer
 * @author Romanovich Andrei
 * @version 1.0
 */
public enum  ServiceErrorCode {
    HN_SERV_000("Cannot get user by id"),
    HN_SERV_001("Cannot add user"),
    HN_SERV_002("Cannot delete user"),
    HN_SERV_003("Cannot update user"),
    HN_SERV_004("Cannot get user by login and password"),
    HN_SERV_005("Cannot get all users"),
    HN_SERV_006("Cannot update book"),
    HN_SERV_007("Cannot get book by id"),
    HN_SERV_008("Cannot get all books"),
    HN_SERV_009("Cannot delete book"),
    HN_SERV_010("Cannot add book"),
    HN_SERV_011("Cannot update category"),
    HN_SERV_012("Cannot get category by id"),
    HN_SERV_013("Cannot get all categories"),
    HN_SERV_014("Cannot delete category"),
    HN_SERV_015("Cannot add category"),
    HN_SERV_016("Cannot get books by start and end positions"),
    HN_SERV_017("Cannot get count books");


    private final String value;

    private ServiceErrorCode(String s) {
        value = s;
    }

    public boolean equalsValue(String value2) {
        return (value2 != null) && value.equals(value2);
    }

    public String toString() {
        return value;
    }
}
