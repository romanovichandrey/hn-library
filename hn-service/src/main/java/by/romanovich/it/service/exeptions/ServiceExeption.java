package by.romanovich.it.service.exeptions;

/**
 * Create custom exception on service layer.
 * @author Romanovich Andrey
 * @version 1.0
 */
public class ServiceExeption extends Exception {

    private ServiceErrorCode code;

    private Object[] params;

    private String message;

    public ServiceExeption(Exception e, ServiceErrorCode code, Object... params) {
        super(e);
        this.code = code;
        this.params = params;
        message = String.format(code.toString(), params);
    }

    public ServiceErrorCode getCode() {
        return code;
    }

    public void setCode(ServiceErrorCode code) {
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
