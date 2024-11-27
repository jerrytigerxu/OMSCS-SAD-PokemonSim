package cs6310.Pokemon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.retry.annotation.Retryable;

import cs6310.Pokemon.dto.BattleResult;
import cs6310.Pokemon.dto.TournamentResult;
import cs6310.Pokemon.exception.InvalidSeedException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.io.File;
import java.sql.SQLException;
import org.reflections.Reflections;
import cs6310.Pokemon.dto.Pokemon;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandService {
    private final Battle battle;
    private final ApplicationContext applicationContext;

    @Setter
    private int seed = -1;

    public void processCommand(String inputString) {
        var delimiter = ",";

        var tokens = inputString.split(delimiter);
        System.out.println("> " + inputString);

        if (tokens[0].startsWith("//")) {
            // do nothing
        } else if (tokens[0].equals("setseed")) {
            doSetSeed(Integer.parseInt(tokens[1]));
        } else if (tokens[0].equals("removeseed")) {
            // processRemoveSeed(tokens);
        } else if (tokens[0].equals("battle")) {
            try {
                doBattle(tokens[1], tokens[2]);
            } catch (InvalidSeedException e) {
                e.printStackTrace();
            }
            try {
                doBattle(tokens[1], tokens[2]);
            } catch (InvalidSeedException e) {
                e.printStackTrace();
            }
        } else if (tokens[0].equals("tournament")) {
            // processTournament(tokens);
        } else if (tokens[0].equals("displayinfo")) {
            doDisplayInfo(tokens[1]);
        } else if (tokens[0].equals("stop")) {
            doStop();
            return;
        } else {
            System.out.println("command unknown");
            return;
        }
    }

    public void ProcessCommands(String[] args) {
        printIntro();
        var commandLineInput = new Scanner(System.in);
        var delimiter = ",";

        while (true) {
            try {
                var wholeInputLine = commandLineInput.nextLine();
                var tokens = wholeInputLine.split(delimiter);
                System.out.println("> " + wholeInputLine);

                if (tokens[0].startsWith("//")) {
                    // for (var i = 0; i < tokens.length; i++) {
                    // System.out.println("token " + i + " is " + tokens[i]);
                    // }
                } else if (tokens[0].equals("setseed")) {
                    doSetSeed(Integer.parseInt(tokens[1]));
                } else if (tokens[0].equals("removeseed")) {
                    // processRemoveSeed(tokens);
                } else if (tokens[0].equals("battle")) {
                    doBattle(tokens[1], tokens[2]);
                } else if (tokens[0].equals("tournament")) {
                    // processTournament(tokens);
                } else if (tokens[0].equals("displayinfo")) {
                    doDisplayInfo(tokens[1]);
                } else if (tokens[0].equals("stop")) {
                    doStop();
                    break;
                } else {
                    System.out.println("command unknown");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }

        System.out.println("simulation terminated");
        commandLineInput.close();
    }

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
            System.out.println(pokemonInstance.toString());
            return pokemonInstance.toString();
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
