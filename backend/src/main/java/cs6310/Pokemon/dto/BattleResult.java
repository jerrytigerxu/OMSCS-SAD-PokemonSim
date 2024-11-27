package cs6310.Pokemon.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Transient;
import jakarta.persistence.ManyToOne;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
public class BattleResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("winnerPokemon")
    private Pokemon winnerPokemon;
    @JsonProperty("loserPokemon")
    private Pokemon loserPokemon;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdTimestamp;

    @ManyToOne
    @JsonIgnore
    private TournamentResult tournamentResult;

    @Transient
    private List<String> orderOfBattle = new ArrayList<>();

    public BattleResult() {
    }

    public BattleResult(Pokemon winnerPokemon, Pokemon loserPokemon, TournamentResult tournamentResult, LocalDateTime createdTimestamp, List<String> orderOfBattle) {
        this.winnerPokemon = winnerPokemon;
        this.loserPokemon = loserPokemon;
        this.tournamentResult = tournamentResult;
        this.createdTimestamp = createdTimestamp;
        this.orderOfBattle = orderOfBattle;
    }

    public BattleResult(Pokemon winnerPokemon, Pokemon loserPokemon) {
        this.winnerPokemon = winnerPokemon;
        this.loserPokemon = loserPokemon;
        this.tournamentResult = null;
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
        return "BattleResult{" +
                "winnerPokemon=" + (winnerPokemon != null ? winnerPokemon.getName() : "") +
                ", loserPokemon=" + (loserPokemon != null ? loserPokemon.getName() : "") +
                ", createdTimestamp=" + createdTimestamp +
                ", orderOfBattle=" + orderOfBattle +
                '}';
    }
}
