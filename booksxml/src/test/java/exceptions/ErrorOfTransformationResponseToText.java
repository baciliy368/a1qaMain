package exceptions;

public class ErrorOfTransformationResponseToText extends Error {
    public ErrorOfTransformationResponseToText() {
        super("Can't transform response to String");
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
