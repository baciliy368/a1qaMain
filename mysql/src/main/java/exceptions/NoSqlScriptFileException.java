package exceptions;

public class NoSqlScriptFileException extends Error {

    public NoSqlScriptFileException(String filePath) {
        super("An error occurred while reading the sql file " + filePath);
    }
}
