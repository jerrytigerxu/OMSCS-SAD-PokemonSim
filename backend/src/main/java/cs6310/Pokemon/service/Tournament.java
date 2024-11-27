package cs6310.Pokemon.service;

import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import cs6310.Pokemon.model.dto.BattleResult;
import cs6310.Pokemon.model.dto.TournamentResult;

public class Tournament {
    @SuppressWarnings("unused")
    private List<String> pokemonList; // TODO: May not need this field
    private Queue<String> pokemonQueue;
    private Integer seed;

    public Tournament(Integer seed, List<String> pokemonList) {
        this.seed = seed;
        this.pokemonList = pokemonList;
        this.pokemonQueue = new LinkedList<>(pokemonList);
    }

    public Tournament(List<String> pokemonList) {
        this.pokemonList = pokemonList;
        this.pokemonQueue = new LinkedList<>(pokemonList);
    }

    public TournamentResult startTournament() {
        TournamentResult tournamentResult = new TournamentResult();
        while (pokemonQueue.size() > 1) {
            System.out.println("Starting tournament simulation...");
            String pokemonOne = pokemonQueue.poll();
            String pokemonTwo = pokemonQueue.poll();
            Battle battle = new Battle();
            if (seed != null) {
                battle.setSeed(seed);
            }
            BattleResult battleResult = battle.startBattle(pokemonOne, pokemonTwo);
            tournamentResult.getBattleResultList().add(battleResult);
            pokemonQueue.add(battleResult.getWinnerPokemon());
        }
        tournamentResult.setWinnerPokemon(pokemonQueue.poll());
        System.out.println("Tournament simulation completed");
        return tournamentResult;
    }

}
