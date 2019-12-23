package exceptions;

public class NoPropertiesFileException extends Error {
    public NoPropertiesFileException() {
        super("missing properties file, check path and file availability");
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
