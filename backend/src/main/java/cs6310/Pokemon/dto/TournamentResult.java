package cs6310.Pokemon.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
public class TournamentResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("winnerPokemon")
    private String winnerPokemon;

    @OneToMany
    @JsonProperty("battleResultList")
    private List<BattleResult> battleResultList;
}
