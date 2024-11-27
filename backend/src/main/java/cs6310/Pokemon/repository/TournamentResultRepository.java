package cs6310.Pokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cs6310.Pokemon.model.entity.TournamentResultEntity;

public interface TournamentResultRepository extends JpaRepository<TournamentResultEntity, Long> {
}