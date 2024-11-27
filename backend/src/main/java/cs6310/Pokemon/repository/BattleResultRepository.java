package cs6310.Pokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cs6310.Pokemon.dto.BattleResult;

public interface BattleResultRepository extends JpaRepository<BattleResult, Long> {
}