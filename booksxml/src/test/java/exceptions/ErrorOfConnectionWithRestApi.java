package exceptions;

public class ErrorOfConnectionWithRestApi extends Error {
    public ErrorOfConnectionWithRestApi() {
        super("Error with connection with RestApi");
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
