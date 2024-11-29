package cs6310.Pokemon.service;

import java.util.ArrayList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs6310.Pokemon.model.dto.BattleResult;
import cs6310.Pokemon.model.dto.TournamentResult;
import cs6310.Pokemon.model.entity.BattleResultEntity;
import cs6310.Pokemon.model.entity.TournamentResultEntity;
import cs6310.Pokemon.repository.BattleResultRepository;
import cs6310.Pokemon.repository.TournamentResultRepository;
import jakarta.transaction.Transactional;
import lombok.Data;

@Service
@Data
public class Tournament {
    private Queue<String> pokemonQueue;
    private Integer seed;

    private final TournamentResultRepository tournamentResultRepository;
    private final BattleResultRepository battleResultRepository;
    private final Battle battle;

    @Autowired
    public Tournament(TournamentResultRepository tournamentResultRepository,
            BattleResultRepository battleResultRepository, Battle battle) {
        this.tournamentResultRepository = tournamentResultRepository;
        this.battleResultRepository = battleResultRepository;
        this.battle = battle;
    }

    @Transactional
    public TournamentResult startTournament() {
        TournamentResult tournamentResult = new TournamentResult();
        var battleResults = new ArrayList<BattleResult>();
        while (pokemonQueue.size() > 1) {
            System.out.println("Starting tournament simulation...");
            String pokemonOne = pokemonQueue.poll();
            String pokemonTwo = pokemonQueue.poll();

            if (seed != null) {
                battle.setSeed(seed);
            }
            BattleResult battleResult = battle.startBattle(pokemonOne, pokemonTwo);
            battleResults.add(battleResult);

            tournamentResult.getBattleResultList().add(battleResult);
            pokemonQueue.add(battleResult.getWinnerPokemon());
        }
        tournamentResult.setWinnerPokemon(pokemonQueue.poll());

        var battleResultEntityList = new ArrayList<BattleResultEntity>();
        var tournamentResultEntity = new TournamentResultEntity();

        saveBattleResults(battleResults, battleResultEntityList, tournamentResultEntity);
        saveTournamentResults(tournamentResult, battleResultEntityList, tournamentResultEntity);

        System.out.println("The winner is: " + tournamentResult.getWinnerPokemon());
        System.out.println("Tournament simulation completed");
        return tournamentResult;
    }

    @Transactional
    public void saveTournamentResults(TournamentResult tournamentResult,
            ArrayList<BattleResultEntity> battleResultEntityList,
            TournamentResultEntity tournamentResultEntity) {
        tournamentResultEntity.setWinnerPokemon(tournamentResult.getWinnerPokemon());
        tournamentResultEntity.setBattleResultEntityList(battleResultEntityList);
        tournamentResultRepository.save(tournamentResultEntity);
    }

    @Transactional
    public void saveBattleResults(ArrayList<BattleResult> battleResults,
            ArrayList<BattleResultEntity> battleResultEntityList,
            TournamentResultEntity tournamentResultEntity) {
        tournamentResultRepository.save(tournamentResultEntity);
        battleResults.forEach(result -> {
            var entity = new BattleResultEntity();
            entity.setWinnerPokemon(result.getWinnerPokemon());
            entity.setLoserPokemon(result.getLoserPokemon());
            entity.setTournamentResultEntity(tournamentResultEntity);

            battleResultEntityList.add(entity);
        });
        battleResultRepository.saveAllAndFlush(battleResultEntityList);
    }
}
