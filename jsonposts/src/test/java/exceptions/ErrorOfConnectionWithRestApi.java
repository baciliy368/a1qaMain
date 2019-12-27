package exceptions;

public class ErrorOfConnectionWithRestApi extends Error {
    public ErrorOfConnectionWithRestApi(Exception error) {
        super("Error with connection with RestApi\n" + error);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
