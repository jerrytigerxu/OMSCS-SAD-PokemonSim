package cs6310.Pokemon.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class Result {
    Pokemon winnerPokemon;
    Pokemon loserPokemon;
    //String orderOfBattle = "";
    Long tournamentId;
    LocalDateTime createdTimestamp;

    private List<String> orderOfBattle = new ArrayList<>(); 

    public Result() {
    }

    public Result(Pokemon winnerPokemon, Pokemon loserPokemon, Long tournamentId, LocalDateTime createdTimestamp, List<String> orderOfBattle) {
        this.winnerPokemon = winnerPokemon;
        this.loserPokemon = loserPokemon;
        this.tournamentId = tournamentId;
        this.createdTimestamp = createdTimestamp;
        this.orderOfBattle = orderOfBattle;
    }

    public Result(Pokemon winnerPokemon, Pokemon loserPokemon) {
        this.winnerPokemon = winnerPokemon;
        this.loserPokemon = loserPokemon;
        this.tournamentId = null;
        this.createdTimestamp = LocalDateTime.now();
        this.orderOfBattle = new ArrayList<>();
    }

    public List<String> getOrderOfBattle() {
        return orderOfBattle;
    }

    public void setOrderOfBattle(List<String> orderOfBattle) {
        this.orderOfBattle = orderOfBattle;
    }

    @Override
    public String toString() {
        // Format orderOfBattle as a JSON array
        String orderOfBattleJson = orderOfBattle.stream()
                .map(entry -> "\"" + entry + "\"")
                .collect(Collectors.joining(", ", "[", "]"));

        return "{ \"winnerPokemon\": \"" + (winnerPokemon != null ? winnerPokemon.getName() : "") + "\", " +
                "\"loserPokemon\": \"" + (loserPokemon != null ? loserPokemon.getName() : "") + "\", " +
                "\"tournamentId\": " + tournamentId + ", " +
                "\"createdTimestamp\": \"" + createdTimestamp + "\", " +
                "\"orderOfBattle\": " + orderOfBattleJson + " }";
    }
}
