package exceptions;

public class NoConfigFileOrParameterException extends Error {
    public NoConfigFileOrParameterException(String directoryPath) {
        super("There are no config file in directory" + directoryPath);
    }
}
