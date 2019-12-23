package exceptions;

public class NoResultFileException extends Error{
    public NoResultFileException(String stacktrace) {
        super("No result file in project folder" + stacktrace);
    }
}
