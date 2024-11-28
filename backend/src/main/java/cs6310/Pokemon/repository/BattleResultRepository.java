package cs6310.Pokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cs6310.Pokemon.model.entity.BattleResultEntity;
import org.springframework.stereotype.Repository; 

import java.time.LocalDateTime;

public interface BattleResultRepository extends JpaRepository<BattleResultEntity, Long> {
    int deleteByCreatedTimestampBefore(LocalDateTime retentionCutoff);
}