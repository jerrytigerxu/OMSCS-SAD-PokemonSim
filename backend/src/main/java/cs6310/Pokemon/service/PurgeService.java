package cs6310.Pokemon.service;

import org.springframework.stereotype.Service; 
import org.springframework.scheduling.annotation.Scheduled;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.transaction.annotation.Transactional;

import cs6310.Pokemon.repository.BattleResultRepository;
import cs6310.Pokemon.repository.TournamentResultRepository; 

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

@Service 
public class PurgeService {
    private static final Logger logger = LoggerFactory.getLogger(PurgeService.class);
    private final BattleResultRepository battleResultRepository;
    private final TournamentResultRepository tournamentResultRepository;

    @Value("${data.retention.period.months:3}") // Data retention period is currently set to 3 months
    private int dataRetentionPeriodMonths;

    public PurgeService(BattleResultRepository battleResultRepository, TournamentResultRepository tournamentResultRepository) {
        this.battleResultRepository = battleResultRepository;
        this.tournamentResultRepository = tournamentResultRepository;
    }

    @Transactional
    // @Scheduled(cron = "*/30 * * * * *") // This CRON expression means that it triggers the methods daily at 3am EST
    @SchedulerLock(name = "purgeOldBattleResults", lockAtMostFor = "10m", lockAtLeastFor = "10m")
    public void purgeOldBattleResults() {
        try {
            LocalDateTime retentionCutoff = LocalDateTime.now().minus(dataRetentionPeriodMonths, ChronoUnit.MONTHS);
            int deletedCount = battleResultRepository.deleteByCreatedTimestampBefore(retentionCutoff);
            logger.info("Purged {} old battle results.", deletedCount);   
        } catch (Exception e) {
            logger.error("Error purging old battle results", e);
        }
    }

    @Transactional
    @Scheduled(cron = "0 0 3 * * ?") // This CRON expression means that it triggers the methods daily at 3am EST
    @SchedulerLock(name = "purgeOldTournamentResults", lockAtMostFor = "10m", lockAtLeastFor = "10m")
    public void purgeOldTournamentResults() {
        try {
            LocalDateTime retentionCutoff = LocalDateTime.now().minus(dataRetentionPeriodMonths, ChronoUnit.MONTHS);
            int deletedCount = tournamentResultRepository.deleteByCreatedTimestampBefore(retentionCutoff);
            logger.info("Purged {} old tournament results.", deletedCount);   
        } catch (Exception e) {
            logger.error("Error purging old tournament results", e);
        }
    }

    
}
