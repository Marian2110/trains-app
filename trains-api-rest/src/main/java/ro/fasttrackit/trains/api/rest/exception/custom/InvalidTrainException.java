package ro.fasttrackit.trains.api.rest.exception.custom;

public class InvalidTrainException extends RuntimeException {
    public InvalidTrainException(String message) {
        super(message);
    }
}
