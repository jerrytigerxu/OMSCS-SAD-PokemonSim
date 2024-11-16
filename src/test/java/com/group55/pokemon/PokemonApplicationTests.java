package com.group55.pokemon;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.group55.pokemon.service.CommandProcessor;
import com.group55.pokemon.service.PokemonFactory;
import com.group55.pokemon.service.Battle;

@SpringBootTest(classes = PokemonApplication.class)
class PokemonApplicationTests {

	@Autowired
	Battle Battle;

	@Autowired
	PokemonFactory pokemonFactory;

	private CommandProcessor commandProcessor;

	@Test
	void commands_00()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		commandProcessor = new CommandProcessor(Battle, pokemonFactory);

		commandProcessor.processSetSeed(new String[] { "setseed", "1" });
		commandProcessor.processBattle(new String[] { "battle", "Charmander", "Squirtle" });
		commandProcessor.processStop(new String[] { "stop" });
	}
}
