package cs6310.Pokemon.exception;

public class InvalidSeedException extends Exception {
    public InvalidSeedException() {
        super("Seed is not set or is invalid!");
    }
}
