package cs6310.Pokemon.model.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Setter;

@Data
@Setter
@Entity
@Table(name = "battle_result")
public class BattleResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String winnerPokemon;
    private String loserPokemon;
    private String orderOfBattle;

    @CreationTimestamp
    private LocalDateTime createdTimestamp;

    @ManyToOne
    private TournamentResultEntity tournamentResultEntity;
}