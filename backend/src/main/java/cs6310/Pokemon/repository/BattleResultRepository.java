package cs6310.Pokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cs6310.Pokemon.model.entity.BattleResultEntity;

public interface BattleResultRepository extends JpaRepository<BattleResultEntity, Long> {
}