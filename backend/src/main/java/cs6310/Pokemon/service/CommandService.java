package cs6310.Pokemon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import cs6310.Pokemon.dto.Result;
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
                    doSetSeed(tokens[1]);
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
                    doSetSeed(tokens[1]);
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

    public String doSetSeed(String seed) {
        this.seed = Integer.parseInt(seed);
        return "Seed set!";
    }

    public String doRemoveSeed() {
        this.seed = -1;
        return "Seed removed!";
    }

    public Result doBattle(String pokemonOne, String pokemonTwo) {
        if (this.seed < 0) {
            throw new IllegalArgumentException("Seed not set");
        }
        battle.setSeed(this.seed);
        return battle.startBattle(pokemonOne, pokemonTwo);
    }

    public Result doTournament(List<String> pokemonList) {
        if (this.seed < 0) {
            throw new IllegalArgumentException("Seed not set");
        }
        // TODO: implement this method
        return new Result();
    }

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
        return List.of("Charmander","Pikachu","Squirtle","Bulbasaur");
    }
}
