package cs6310.Pokemon.service;

import java.util.Arrays;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs6310.Pokemon.exception.InvalidSeedException;

@Component
public class CommandParser {

    @Autowired
    private CommandService commandService;

    public void processCommand(String inputString) {
        var delimiter = ",";

        var tokens = inputString.split(delimiter);
        System.out.println("> " + inputString);

        if (tokens[0].startsWith("//")) {
            // do nothing
        } else if (tokens[0].equals("setseed")) {
            commandService.doSetSeed(Integer.parseInt(tokens[1]));
        } else if (tokens[0].equals("removeseed")) {
            commandService.doRemoveSeed();
        } else if (tokens[0].equals("battle")) {
            try {
                commandService.doBattle(tokens[1], tokens[2]);
            } catch (InvalidSeedException e) {
                e.printStackTrace();
            }
        } else if (tokens[0].equals("tournament")) {
            try {
                commandService.doTournament(Arrays.asList(tokens).subList(1, tokens.length));
            } catch (InvalidSeedException e) {
                e.printStackTrace();
            }
        } else if (tokens[0].equals("displayinfo")) {
            commandService.doDisplayInfo(tokens[1]);
        } else if (tokens[0].equals("stop")) {
            commandService.doStop();
            return;
        } else {
            System.out.println("command unknown");
            return;
        }
    }

    public void processCommands(String[] args) {
        CommandService.printIntro();
        var commandLineInput = new Scanner(System.in);

        while (true) {
            try {
                var wholeInputLine = commandLineInput.nextLine();
                if (wholeInputLine.startsWith("stop")) {
                    break;
                }
                processCommand(wholeInputLine);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }

        System.out.println("simulation terminated");
        commandLineInput.close();
    }
}
