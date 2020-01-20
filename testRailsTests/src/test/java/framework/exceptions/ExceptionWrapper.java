package framework.exceptions;

public class ExceptionWrapper extends Error {
    public ExceptionWrapper(String message, Throwable cause) {
        super(message, cause);
    }
}
