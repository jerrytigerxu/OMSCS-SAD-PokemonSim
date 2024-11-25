package cs6310.Pokemon.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Result {
    Pokemon winnerPokemon;
    Pokemon loserPokemon;
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

        // Needed to get the names of the Pokemon and not the entire object
        // This fixes the JSON issue

        String battleHistoryJson = battleHistory.stream()
                    .map(entry -> "\"" + entry + "\"")
                    .collect(Collectors.joining(", ", "[", "]"));

        return "{ \"winnerPokemon\": \"" + (winnerPokemon != null ? winnerPokemon.getName() : "") + "\", " +
           "\"loserPokemon\": \"" + (loserPokemon != null ? loserPokemon.getName() : "") + "\", " +
           "\"tournamentId\": " + tournamentId + ", " +
           "\"createdTimestamp\": \"" + createdTimestamp + "\", " +
           "\"battleHistory\": " + battleHistoryJson + " }";

        /* 
        return "Result{" +
                "winnerPokemon=" + winnerPokemon +
                ", loserPokemon=" + loserPokemon +
                ", tournamentId=" + tournamentId +
                ", createdTimestamp=" + createdTimestamp +
                '}';

        */
    }

    // Getter and setter for battleHistory
    public List<String> getBattleHistory() {
        return battleHistory;
    }

    public void setBattleHistory(List<String> battleHistory) {
        this.battleHistory = battleHistory;
    }

    // Setters for winnerPokemon and loserPokemon
    public void setWinnerPokemon(Pokemon winnerPokemon) {
        this.winnerPokemon = winnerPokemon;
    }

    public void setLoserPokemon(Pokemon loserPokemon) {
        this.loserPokemon = loserPokemon;
    }

}
