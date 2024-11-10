package com.group55.pokemon.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommandProcessor {
    private static Integer SEED = 12345;

    public void ProcessCommands(String[] args) {
        var commandLineInput = new Scanner(System.in);  
        var delimiter = ",";

        while (true) {
            try {
                var wholeInputLine = commandLineInput.nextLine();
                var tokens = wholeInputLine.split(delimiter);

                System.out.println("> " + wholeInputLine);

                if (tokens[0].startsWith("/t/")) {
                    if (tokens.length > 1) {
                        try {
                            SEED = Integer.parseInt(tokens[1]);
                            System.out.println("Seed set to " + SEED);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid seed value");
                        }
                    } else {
                        System.out.println("No seed value provided");
                    }
                } else if (tokens[0].equals("setseed")) {
                    for (var i = 0; i < tokens.length; i++) {
                        System.out.println("token " + i + " is " + tokens[i]);
                    }
                } else if (tokens[0].equals("removeseed")) {
                    for (var i = 0; i < tokens.length; i++) {
                        System.out.println("token " + i + " is " + tokens[i]);
                    }
                } else if (tokens[0].equals("battle")) {
                    for (var i = 0; i < tokens.length; i++) {
                        System.out.println("token " + i + " is " + tokens[i]);
                    }
                } else if (tokens[0].equals("tournament")) {
                    for (var i = 0; i < tokens.length; i++) {
                        System.out.println("token " + i + " is " + tokens[i]);
                    }
                } else if (tokens[0].equals("displayinfo")) {
                    for (var i = 0; i < tokens.length; i++) {
                        System.out.println("token " + i + " is " + tokens[i]);
                    }
                } else if (tokens[0].equals("stop")) {
                    for (var i = 0; i < tokens.length; i++) {
                        System.out.println("token " + i + " is " + tokens[i]);
                    }
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
}
