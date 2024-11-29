package cs6310.Pokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cs6310.Pokemon.model.entity.BattleResultEntity;
import java.time.LocalDateTime;

@Repository
public interface BattleResultRepository extends JpaRepository<BattleResultEntity, Long> {
    int deleteByCreatedTimestampBefore(LocalDateTime retentionCutoff);
}