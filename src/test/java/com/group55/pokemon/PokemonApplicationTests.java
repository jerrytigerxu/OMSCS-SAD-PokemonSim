package com.group55.pokemon;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.group55.pokemon.service.CommandProcessor;
import com.group55.pokemon.service.PokemonFactory;
import com.group55.pokemon.service.Battle;

@SpringBootTest(classes = PokemonApplication.class)
class PokemonApplicationTests {

	@Autowired
	Battle Battle;

	@Autowired
	PokemonFactory pokemonFactory;

	@Autowired
	ApplicationContext applicationContext;

	private CommandProcessor commandProcessor;

	@Test
	void onePokemonExistsOneDoesNot()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		commandProcessor = new CommandProcessor(Battle, pokemonFactory, applicationContext);

		pokemonFactory.createPokemon("Charmander");
		commandProcessor.processSetSeed(new String[] { "setseed", "1" });
		commandProcessor.processBattle(new String[] { "battle", "Charmander", "Squirtle" });
		commandProcessor.processStop(new String[] { "stop" });
	}

	@Test
	void command_00()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		commandProcessor = new CommandProcessor(Battle, pokemonFactory, applicationContext);

		pokemonFactory.createPokemon("Charmander");
		commandProcessor.processSetSeed(new String[] { "setseed", "1" });
		commandProcessor.processBattle(new String[] { "battle", "Charmander", "Squirtle" });
		commandProcessor.processStop(new String[] { "stop" });
	}
}
