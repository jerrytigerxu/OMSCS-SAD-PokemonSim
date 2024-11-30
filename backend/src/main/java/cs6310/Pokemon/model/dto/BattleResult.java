package cs6310.Pokemon.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Setter
public class BattleResult {
    @JsonProperty("winnerPokemon")
    private String winnerPokemon;
    @JsonProperty("loserPokemon")
    private String loserPokemon;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdTimestamp;

    @JsonProperty("orderOfBattle")
    private List<String> orderOfBattle = new ArrayList<>();

    public BattleResult() {
    }

    public BattleResult(String winnerPokemon, String loserPokemon, LocalDateTime createdTimestamp,
            List<String> orderOfBattle) {
        this.winnerPokemon = winnerPokemon;
        this.loserPokemon = loserPokemon;
        this.createdTimestamp = createdTimestamp;
        this.orderOfBattle = orderOfBattle;
    }

    public BattleResult(String winnerPokemon, String loserPokemon) {
        this.winnerPokemon = winnerPokemon;
        this.loserPokemon = loserPokemon;
        this.createdTimestamp = LocalDateTime.now();
        this.orderOfBattle = new ArrayList<>();
    }

    public List<String> getOrderOfBattle() {
        return orderOfBattle;
    }

    public void setOrderOfBattle(List<String> orderOfBattle) {
        this.orderOfBattle = orderOfBattle;
    }
}
