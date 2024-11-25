package cs6310.Pokemon.exceptions;

public class InvalidSeedException extends Exception {
    public InvalidSeedException() {
        super("Seed is not set or is invalid!");
    }
}
