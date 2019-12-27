package exceptions;

public class ErrorOfTransformationResponseToText extends Error {
    public ErrorOfTransformationResponseToText(Exception e) {
        super("Can't transform response to String\n" + e);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
