package com.group55.pokemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.group55.pokemon.service.CommandProcessor;

@SpringBootApplication(scanBasePackages = { "com.group55.pokemon.service", "com.group55.pokemon.dto" })
public class PokemonApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(PokemonApplication.class, args);
		printIntro();
		var commandProcessor = context.getBean(CommandProcessor.class);
		commandProcessor.ProcessCommands(args);
	}

	static void printIntro() {
		System.out.println("Welcome to the thunder dome!");
	}

}
