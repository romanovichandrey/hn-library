package by.romanovich.it.dao.exeptions;

public class DaoExeption extends Exception {

    private Exception exception;

    public DaoExeption(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
