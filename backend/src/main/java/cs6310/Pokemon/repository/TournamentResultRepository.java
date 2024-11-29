package cs6310.Pokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cs6310.Pokemon.model.entity.TournamentResultEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

public interface TournamentResultRepository extends JpaRepository<TournamentResultEntity, Long> {
    int deleteByCreatedTimestampBefore(LocalDateTime retentionCutoff);
}