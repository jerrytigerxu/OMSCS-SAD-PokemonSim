package cs6310.Pokemon.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.retry.annotation.Retryable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;

import cs6310.Pokemon.model.domain.Pokemon;
import cs6310.Pokemon.model.dto.BattleResult;
import cs6310.Pokemon.model.dto.TournamentResult;
import cs6310.Pokemon.repository.BattleResultRepository;
import cs6310.Pokemon.repository.TournamentResultRepository;
import cs6310.Pokemon.exception.InvalidSeedException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import org.reflections.Reflections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandService {
    private final Battle battle;
    private final Tournament tournament;

    @Setter
    private int seed = -1;

    private BattleResultRepository battleResultRepository;
    private TournamentResultRepository tournamentResultRepository;

    public String doSetSeed(Integer seed) {
        this.seed = seed;
        return "Seed set to " + seed;
    }

    public String doRemoveSeed() {
        this.seed = -1;
        return "Seed removed.";
    }

    public BattleResult doBattle(String pokemonOne, String pokemonTwo) throws InvalidSeedException {
        if (this.seed < 0) {
            throw new InvalidSeedException();
        }
        battle.setSeed(this.seed);
        return battle.startBattle(pokemonOne, pokemonTwo);
    }

    public TournamentResult doTournament(List<String> pokemonList) throws InvalidSeedException {
        if (this.seed < 0) {
            throw new InvalidSeedException();
        }
        try {
            System.out.println("Starting tournament in CommandService with seed: " + this.seed);
            tournament.setSeed(this.seed);
            tournament.setPokemonQueue(new LinkedList<>(pokemonList));
            TournamentResult result = tournament.startTournament();
            System.out.println("Tournament completed successfully.");
            return result;
        } catch (Exception e) {
            System.err.println("Error in doTournament: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Retryable(value = { SQLException.class }, maxAttempts = 3)
    public String doDisplayInfo(String pokemon) {
        try {
            var pokemonClass = Class.forName("cs6310.Pokemon." + pokemon);
            var pokemonInstance = pokemonClass.getDeclaredConstructor(int.class).newInstance(1);
            var displayInfoMethod = pokemonClass.getMethod("displayInfo");
            var info = (String) displayInfoMethod.invoke(pokemonInstance);
            System.out.println(info);
            return info;
        } catch (Exception e) {
            return "Pokemon " + pokemon + " not found";
        }
    }

    public String doStop() {
        System.out.println("stop acknowledged");
        return "stop acknowledged";
    }

    public static void printIntro() {
        System.out.println("Welcome to the thunder dome!");
    }

    public List<String> getAllPokemon() {
        Reflections reflections = new Reflections("cs6310.Pokemon");
        Set<Class<? extends Pokemon>> allClasses = reflections.getSubTypesOf(Pokemon.class);
        // Set<Class<?>> allClasses = reflections.getSubTypesOf(Object.class);
        return allClasses.stream()
                // .filter(cls -> cls.getPackage().getName().equals("cs6310.Pokemon"))
                .map(Class::getSimpleName)
                .collect(Collectors.toList());
    }

    @Retryable(value = { Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public List<String> getAllPokemonRetriable() {
        log.info("Trying to get all pokemon");
        Reflections reflections = new Reflections("cs6310.BackPackageName");
        Set<Class<? extends Pokemon>> allClasses = reflections.getSubTypesOf(Pokemon.class);
        return allClasses.stream().map(Class::getSimpleName).toList();
    }

    @Recover
    public List<String> recover(Exception e) {
        log.error("Failed to get all pokemon", e);
        return List.of("Failed to get all pokemon, please try again later.");
    }
}
