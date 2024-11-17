package cs6310.Pokemon.dto;

import java.time.LocalDateTime;

public class Result {
    Pokemon winnerPokemon;
    Pokemon loserPokemon;
    Long tournamentId;
    LocalDateTime createdTimestamp;

    public Result() {
    }

    public Result(Pokemon winnerPokemon, Pokemon loserPokemon, Long tournamentId, LocalDateTime createdTimestamp) {
        this.winnerPokemon = winnerPokemon;
        this.loserPokemon = loserPokemon;
        this.tournamentId = tournamentId;
        this.createdTimestamp = createdTimestamp;
    }

    public Result(Pokemon winnerPokemon, Pokemon loserPokemon) {
        this.winnerPokemon = winnerPokemon;
        this.loserPokemon = loserPokemon;
        this.tournamentId = null;
        this.createdTimestamp = LocalDateTime.now();
    }
}
