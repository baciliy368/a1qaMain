package exceptions;

public class NoSqlScriptFileException extends Error {

    public NoSqlScriptFileException(String filePath) {
        super("SQLScript is not exist in" + filePath);
    }
}
