package ro.fasttrackit.trains.api.rest.exception.custom;

public class InvalidRouteException extends RuntimeException {
    public InvalidRouteException(String message) {
        super(message);
    }
}
