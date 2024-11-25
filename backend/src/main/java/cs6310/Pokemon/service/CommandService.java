package cs6310.Pokemon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.retry.annotation.Retryable;

import cs6310.Pokemon.dto.Result;
import cs6310.Pokemon.exceptions.InvalidSeedException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.io.File;

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
            // for (var i = 0; i < tokens.length; i++) {
            // System.out.println("token " + i + " is " + tokens[i]);
            // }
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

    public Result doBattle(String pokemonOne, String pokemonTwo) throws InvalidSeedException {
        if (this.seed < 0) {
            throw new InvalidSeedException();
        }
        battle.setSeed(this.seed);
        return battle.startBattle(pokemonOne, pokemonTwo);
    }

<<<<<<< HEAD
    @Retryable(value = { SQLException.class }, maxAttempts = 3)
=======
>>>>>>> 18478a7 (implement battle frontend and backend to show results.)
    public Result doTournament(List<String> pokemonList) throws InvalidSeedException {
        if (this.seed < 0) {
            throw new InvalidSeedException();
        }
        // TODO: implement this method
        return new Result();
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

    public static List<String> getAllPokemon() {
        return List.of("Charmander", "Pikachu", "Squirtle", "Bulbasaur");
    }
}
