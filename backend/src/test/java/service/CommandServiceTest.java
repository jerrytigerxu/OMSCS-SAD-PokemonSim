package service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cs6310.Pokemon.exception.InvalidSeedException;
import cs6310.Pokemon.service.CommandService;
import cs6310.Pokemon.service.PokemonApplication;

@SpringBootTest(classes = PokemonApplication.class)
class CommandServiceTest {
        
        @Autowired
        private CommandService commandService;

        @Test
        void commands_00() throws InvalidSeedException {
            commandService.printIntro();
            commandService.doSetSeed(1);
            commandService.doBattle("Charmander","Squirtle");
            commandService.doStop();
        }

        @Test
        void commands_07() throws InvalidSeedException {
            commandService.printIntro();
            commandService.doSetSeed(54);
            commandService.doBattle("Ditto","Bulbasaur");
            commandService.doStop();
        }

        @Test
        void commands_custom_1() {
            commandService.processCommand("setseed,1");
            commandService.processCommand("battle,Charmander,Squirtle");
        }

        @Test
        void commands_custom_2() {
            commandService.processCommand("setseed,5");
            commandService.processCommand("battle,Charmander,Squirtle");
        }

        @Test
        void commands_custom_3() {
            commandService.processCommand("setseed,1");
            commandService.processCommand("removeseed");
            commandService.processCommand("setseed,10");
            commandService.processCommand("battle,Bulbasaur,Pikachu");
        }

        @Test
        void commands_custom_4() {
            commandService.processCommand("setseed,20");
            commandService.processCommand("battle,Pikachu,Charmander");
        }

        @Test
        void commands_custom_5() {
            commandService.processCommand("setseed,30");
            commandService.processCommand("battle,Bulbasaur,Squirtle");

        }

        @Test
        void commands_custom_6() {
            commandService.processCommand("setseed,5");
            commandService.processCommand("battle,Pikachu,Snorlax");
            commandService.processCommand("battle,Bulbasaur,Pikachu");

            commandService.processCommand("setseed,47");
            commandService.processCommand("battle,Snorlax,Pikachu");

            commandService.processCommand("removeseed");
            commandService.processCommand("setseed,287");
            commandService.processCommand("battle,Snorlax,Squirtle");
        }

        @Test
        void commands_custom_7() {
            commandService.processCommand("setseed,10");
            commandService.processCommand("battle,Mew,Snorlax");

            commandService.processCommand("setseed,67");
            commandService.processCommand("battle,Mew,Snorlax");

            commandService.processCommand("removeseed");

            commandService.processCommand("setseed,77");
            commandService.processCommand("battle,Geodude,Charmander");
        }

        @Test
        void commands_custom_8() {
            commandService.processCommand("setseed,54");
            commandService.processCommand("battle,Ditto,Bulbasaur");
            commandService.processCommand("removeseed");
            commandService.processCommand("setseed,67");
            commandService.processCommand("battle,Ditto,Bulbasaur");
        }

        @Test
        void commands_custom_9() {
            commandService.processCommand("setseed,1");
            commandService.processCommand("battle,Ditto,Bulbasaur");
            
            commandService.processCommand("setseed,2");
            commandService.processCommand("battle,Ditto,Bulbasaur");
            
            commandService.processCommand("setseed,3");
            commandService.processCommand("battle,Ditto,Bulbasaur");
            
            commandService.processCommand("setseed,4");
            commandService.processCommand("battle,Ditto,Bulbasaur");
            
            commandService.processCommand("setseed,5");
            commandService.processCommand("battle,Ditto,Bulbasaur");
        }

        @Test
        void commands_custom_10() {
            commandService.processCommand("setseed,1");
        commandService.processCommand("battle,Ditto,Bulbasaur");
        commandService.processCommand("battle,Bulbasaur,Ditto");
        commandService.processCommand("battle,Pikachu,Charmander");
        commandService.processCommand("battle,Charmander,Pikachu");
        commandService.processCommand("battle,Mew,Squirtle");
        commandService.processCommand("battle,Squirtle,Mew");
        commandService.processCommand("battle,Geodude,Squirtle");
        commandService.processCommand("battle,Squirtle,Geodude");
        commandService.processCommand("battle,Snorlax,Charmander");
        commandService.processCommand("battle,Charmander,Snorlax");
        commandService.processCommand("battle,Snorlax,Slowpoke");
        }
}
