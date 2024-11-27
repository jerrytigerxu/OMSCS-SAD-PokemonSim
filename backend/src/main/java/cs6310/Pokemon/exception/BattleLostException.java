package cs6310.Pokemon.exception;

public class BattleLostException extends Exception {
    private String losingPokemonName = null;

    public BattleLostException(String errorMessage) {
        super(errorMessage);
    }

    public BattleLostException(String errorMessage, String pokemonName) {
        losingPokemonName = pokemonName;
    }

    public String getLosingPokemonName() {
        return losingPokemonName;
    }
}
