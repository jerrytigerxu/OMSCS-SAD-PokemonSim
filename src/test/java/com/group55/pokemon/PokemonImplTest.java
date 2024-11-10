package com.group55.pokemon;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.group55.pokemon.service.PokemonFactory;
import com.group55.pokemon.dto.Pokemon;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PokemonApplication.class)
public class PokemonImplTest {

    @Autowired
    private PokemonFactory pokemonFactory;

    @Test
    void createPikachu() throws Exception {
        var pikachu = pokemonFactory.createPokemon("Pikachu");

        assertNotNull(pikachu);
        assertEquals("Pikachu", pikachu.getName());
        assertEquals(100, pikachu.getCurrentHitPoints());
        assertEquals(100, pikachu.getMaxHitPoints());
        assertEquals(100, pikachu.getCurrentSkillPoints());
        assertEquals(100, pikachu.getMaxSkillPoints());
        assertEquals(0, pikachu.getActiveDefense());
    }
}
