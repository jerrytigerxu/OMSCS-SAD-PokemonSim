package cs6310.Pokemon.exception;

public class BattleLostException extends Exception {
    private String losingPokemonName = null;

    public BattleLostException(String message) {
        super(message);
    }

    public BattleLostException(String message, String pokemon) {
        losingPokemonName = pokemon;
    }

    public String getLosingPokemonName() {
        return losingPokemonName;
    }
}
