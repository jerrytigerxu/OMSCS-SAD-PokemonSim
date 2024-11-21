package service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cs6310.Pokemon.service.CommandService;
import cs6310.Pokemon.service.PokemonApplication;

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

        @Test
        void commands_07() {
            commandService.printIntro();
            commandService.doSetSeed("54");
            commandService.doBattle("Ditto","Bulbasaur");
            commandService.doStop();
        }
}
