package cs6310.Pokemon.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Result {
    Pokemon winnerPokemon;
    Pokemon loserPokemon;
    String orderOfBattle = "";
    Long tournamentId;
    LocalDateTime createdTimestamp;

    private List<String> battleHistory; 

    public Result() {
    }

    public Result(Pokemon winnerPokemon, Pokemon loserPokemon, Long tournamentId, LocalDateTime createdTimestamp, List<String> battleHistory) {
        this.winnerPokemon = winnerPokemon;
        this.loserPokemon = loserPokemon;
        this.tournamentId = tournamentId;
        this.createdTimestamp = createdTimestamp;
        this.battleHistory = battleHistory;
    }

    public Result(Pokemon winnerPokemon, Pokemon loserPokemon) {
        this.winnerPokemon = winnerPokemon;
        this.loserPokemon = loserPokemon;
        this.tournamentId = null;
        this.createdTimestamp = LocalDateTime.now();
        this.battleHistory = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "{\"winnerPokemon\":\"" + winnerPokemon.getName() +
                "\",\"loserPokemon\":\"" + loserPokemon.getName() +
                "\",\"orderOfBattle\":\"" + orderOfBattle + "\"}";
    }
}
