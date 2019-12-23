package exceptions;

public class NoSqlScriptFileException extends Error {

    public NoSqlScriptFileException(String filePath) {
        super("SQL Script is not exist in" + filePath);
    }
}
