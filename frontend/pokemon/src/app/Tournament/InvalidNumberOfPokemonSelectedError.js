class InvalidNumberOfPokemonSelectedError extends Error {
    constructor(message) {
        super(message);
        this.name = "InvalidNumberOfPokemonSelectedError";
    }
}

export default InvalidNumberOfPokemonSelectedError;