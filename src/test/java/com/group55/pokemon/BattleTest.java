package com.group55.pokemon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PokemonApplication.class)
public class BattleTest {
    
    @Test
    void createBattleWithTwoPokemon() {

    }
}
