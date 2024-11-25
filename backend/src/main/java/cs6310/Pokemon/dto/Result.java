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

    @Override
    public String toString() {

        // Needed to get the names of the Pokemon and not the entire object
        // This fixes the JSON issue

        return "{ \"winnerPokemon\": \"" + (winnerPokemon != null ? winnerPokemon.getName() : "") + "\", " +
           "\"loserPokemon\": \"" + (loserPokemon != null ? loserPokemon.getName() : "") + "\", " +
           "\"tournamentId\": " + tournamentId + ", " +
           "\"createdTimestamp\": \"" + createdTimestamp + "\" }";

        /* 
        return "Result{" +
                "winnerPokemon=" + winnerPokemon +
                ", loserPokemon=" + loserPokemon +
                ", tournamentId=" + tournamentId +
                ", createdTimestamp=" + createdTimestamp +
                '}';

        */
    }
}
