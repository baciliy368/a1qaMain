package exceptions;

public class ErrorOfTakingResponseCode extends Error {
    public ErrorOfTakingResponseCode() {
        super("Error with taking of Api`s response code");
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
