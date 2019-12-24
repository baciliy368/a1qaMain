package exceptions;

public class NoCaseForThisEnumException extends Error {
    public NoCaseForThisEnumException() {
        super("Error with taking of Api`s response code");
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
