package exceptions;

public class RideHailingException extends Throwable{
    public RideHailingException() {
        this("");
    }

    public RideHailingException(String message) {
        this(message, null);
    }

    public RideHailingException(String message, Throwable cause) {
        super(message, cause);
    }

    public RideHailingException(Throwable cause) {
        this("", cause);
    }
}
