package exceptions;

public class NoPropertiesFileException extends Error {
    public NoPropertiesFileException(String text) {
        super("missing properties file, check path and file availability" + text);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
