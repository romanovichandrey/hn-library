package by.romanovich.it.exceptions;

/**
 * Enuming message error on web layer.
 * and send response to user.
 * @author Hromenkov Ilya
 * @version 1.0
 */
public enum WebErrorCode {

    NC_WEB_000("Cannot get all books"),
    NC_WEB_001("Cannot get all categories"),
    NC_WEB_002("Cannot save book"),
    NC_WEB_003("Cannot save category"),
    NC_WEB_004("Cannot save user"),
    NC_WEB_005("Cannot get user by login"),
    NC_WEB_006("Cannot get all users");


    private final String value;

    private WebErrorCode(String s) {
        value = s;
    }

    public boolean equalsValue(String value2) {
        return (value2 != null) && value.equals(value2);
    }

    public String toString() {
        return value;
    }
}