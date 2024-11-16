package com.group55.pokemon.service;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.group55.pokemon.dto.Pokemon;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
public class CommandProcessor {
    private final Battle battle;
    private final PokemonFactory pokemonFactory;
    private final ApplicationContext applicationContext;

    @Setter
    private Integer SEED = null;

    public void ProcessCommands(String[] args) {
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
                    processSetSeed(tokens);
                } else if (tokens[0].equals("removeseed")) {
                    processRemoveSeed(tokens);
                } else if (tokens[0].equals("battle")) {
                    processBattle(tokens);
                } else if (tokens[0].equals("tournament")) {
                    processTournament(tokens);
                } else if (tokens[0].equals("displayinfo")) {
                    processDisplayInfo(tokens);
                } else if (tokens[0].equals("stop")) {
                    processStop(tokens);
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

    public void processStop(String[] tokens) {
        System.out.println("stop acknowledged");
    }

    public void processDisplayInfo(String[] tokens) {
        for (var i = 0; i < tokens.length; i++) {

            System.out.println("token " + i + " is " + tokens[i]);
        }
    }

    public void processTournament(String[] tokens) {
        for (var i = 0; i < tokens.length; i++) {
            System.out.println("token " + i + " is " + tokens[i]);
        }
    }

    public void processBattle(String[] tokens) {
        for (var i = 0; i < tokens.length; i++) {
            if (i == 1) {
                battle.setPokemonOne(getOrCreatePokemon(tokens[i]));
            } else if (i == 2) {
                battle.setPokemonTwo(getOrCreatePokemon(tokens[i]));
            }
        }

    }

    private Pokemon getOrCreatePokemon(String pokemonName) {
        try {
            if (!applicationContext.containsBean(pokemonName)) {
                System.out.println("Pokemon does not exist");
                return pokemonFactory.createPokemon(pokemonName);
            } else {
                System.out.println("Pokemon already exists!");
                return applicationContext.getBean(pokemonName, Pokemon.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void processRemoveSeed(String[] tokens) {
        setSEED(null);
        battle.setSeed(null);
    }

    public void processSetSeed(String[] tokens) {
        try {
            System.out.println("Setting seed to " + tokens[1]);
            setSEED(Integer.valueOf(tokens[1]));
            battle.setSeed(Integer.valueOf(tokens[1]));
        } catch (NumberFormatException e) {
            System.out.println("Invalid seed value");
        }
    }
}
