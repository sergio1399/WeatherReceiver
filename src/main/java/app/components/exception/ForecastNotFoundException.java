package app.components.exception;

public class ForecastNotFoundException extends RuntimeException {

    public ForecastNotFoundException(String message) {
        super(message);
    }
}
