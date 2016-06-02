package by.romanovich.it.dao.exceptions;

/**
 * Create custom exceptions on dao layer.
 * @author Romanovich Andrey
 * @version 1.0
 */
public class DaoException extends Exception {

    private DaoErrorCode code;

    private Object[] params;

    private String message;

    public DaoException(Exception e, DaoErrorCode code, Object... params) {
        super(e);
        this.code = code;
        this.params = params;
        this.message = String.format(code.toString(), params);
    }

    public DaoErrorCode getCode() {
        return code;
    }

    public void setCode(DaoErrorCode code) {
        this.code = code;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
