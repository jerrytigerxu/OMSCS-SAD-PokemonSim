package cs6310.Pokemon.service;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import cs6310.Pokemon.dto.Pokemon;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
public class CommandService {
    private final Battle battle;
    private final ApplicationContext applicationContext;

    @Setter
    private Integer SEED = null;

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
                    for (var i = 0; i < tokens.length; i++) {
                        System.out.println("token " + i + " is " + tokens[i]);
                    }
                } else if (tokens[0].equals("setseed")) {
                    doSetSeed(tokens[1]);
                } else if (tokens[0].equals("removeseed")) {
                    // processRemoveSeed(tokens);
                } else if (tokens[0].equals("battle")) {
                    doBattle(tokens[1],tokens[2]);
                } else if (tokens[0].equals("tournament")) {
                    // processTournament(tokens);
                } else if (tokens[0].equals("displayinfo")) {
                    // processDisplayInfo(tokens);
                } else if (tokens[0].equals("stop")) {
                    // processStop(tokens);
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

    void doSetSeed(String seed) {
        SEED = Integer.parseInt(seed);
    }

    void doBattle(String pokemonOne, String pokemonTwo) {
        battle.setSeed(SEED);
        battle.startBattle(pokemonOne, pokemonTwo);
    }

    static void printIntro() {
		System.out.println("Welcome to the thunder dome!");
	}
}
