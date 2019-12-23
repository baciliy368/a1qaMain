package exceptions;

public class ErrorOfConnectionWithRestAli extends Error {
    public ErrorOfConnectionWithRestAli() {
        super("Error with connection with RestApi");
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
