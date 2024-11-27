
package cs6310.Pokemon.model.dto;

import java.util.List;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class TournamentResult {
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("winnerPokemon")
    private String winnerPokemon;

    @JsonProperty("battleResultList")
    private List<BattleResult> battleResultList;
}