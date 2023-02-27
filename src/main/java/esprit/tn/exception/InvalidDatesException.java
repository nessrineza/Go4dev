package esprit.tn.exception;

public class InvalidDatesException extends RuntimeException {
    public InvalidDatesException() {
    }

    public InvalidDatesException(String message) {
        super(message);
    }
}
