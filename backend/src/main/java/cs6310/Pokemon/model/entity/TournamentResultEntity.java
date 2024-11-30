package cs6310.Pokemon.model.entity;

import java.util.List;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Setter;

@Data
@Setter
@Entity
@Table(name = "TournamentResult")
public class TournamentResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String winnerPokemon;

    @OneToMany(mappedBy = "tournamentResultEntity")
    private List<BattleResultEntity> battleResultEntityList;

    @CreationTimestamp
    private LocalDateTime createdTimestamp;
}
