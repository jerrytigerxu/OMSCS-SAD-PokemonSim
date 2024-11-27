package cs6310.Pokemon.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.retry.annotation.Retryable;

import cs6310.Pokemon.dto.BattleResult;
import cs6310.Pokemon.dto.TournamentResult;
import cs6310.Pokemon.exception.InvalidSeedException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.sql.SQLException;
import org.reflections.Reflections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandService {
    private final Battle battle;
    @Setter
    private int seed = -1;

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

    @Retryable(value = { SQLException.class }, maxAttempts = 3)
    public TournamentResult doTournament(List<String> pokemonList) throws InvalidSeedException {
        if (this.seed < 0) {
            throw new InvalidSeedException();
        }
        Tournament tournament = new Tournament(this.seed, pokemonList);
        return tournament.startTournament();
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
        Set<Class<?>> allClasses = reflections.getSubTypesOf(Object.class);
        return allClasses.stream()
                .filter(cls -> cls.getPackage().getName().equals("cs6310.Pokemon"))
                .map(Class::getSimpleName)
                .collect(Collectors.toList());
    }
}
