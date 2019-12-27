package exceptions;

public class ErrorOfTakingResponseCode extends Error {
    public ErrorOfTakingResponseCode(Exception e) {
        super("Error with taking of Api`s response code\n" + e);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
