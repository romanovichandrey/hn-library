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
    HN_SERV_004("Cannot get user by login and password");


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
