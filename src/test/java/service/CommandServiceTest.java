package service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cs6310.Pokemon.PokemonApplication;
import cs6310.Pokemon.service.CommandService;

@SpringBootTest(classes = PokemonApplication.class)
class CommandServiceTest {
        
        @Autowired
        private CommandService commandService;

        @Test
        void commands_00() {
            commandService.printIntro();
            commandService.doSetSeed("1");
            commandService.doBattle("Charmander","Squirtle");
            commandService.doStop();
        }
}
