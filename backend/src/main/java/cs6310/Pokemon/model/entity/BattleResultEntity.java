
package cs6310.Pokemon.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Setter
public class BattleResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String winnerPokemon;
    private String loserPokemon;
    private LocalDateTime createdTimestamp;

    @ManyToOne
    private TournamentResultEntity tournamentResult;

    @Transient
    private List<String> orderOfBattle = new ArrayList<>();
}