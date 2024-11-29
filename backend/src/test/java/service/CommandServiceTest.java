package service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import cs6310.Pokemon.exception.InvalidSeedException;
import cs6310.Pokemon.service.CommandParser;
import cs6310.Pokemon.service.CommandService;
import cs6310.Pokemon.service.PokemonApplication;

@SpringBootTest(classes = PokemonApplication.class)
@ActiveProfiles("test")
class CommandServiceTest {

    @Autowired
    private CommandService commandService;

    @Autowired
    private CommandParser commandParser;

    @Test
    void commands_00() throws InvalidSeedException {
        CommandService.printIntro();
        commandService.doSetSeed(1);
        commandService.doBattle("Charmander", "Squirtle");
        commandService.doStop();
    }

    @Test
    void commands_07() throws InvalidSeedException {
        CommandService.printIntro();
        commandService.doSetSeed(54);
        commandService.doBattle("Ditto", "Bulbasaur");
        commandService.doStop();
    }

    @Test
    void commands_custom_1() {
        commandParser.processCommand("setseed,1");
        commandParser.processCommand("battle,Charmander,Squirtle");
    }

    @Test
    void commands_custom_2() {
        commandParser.processCommand("setseed,5");
        commandParser.processCommand("battle,Charmander,Squirtle");
    }

    @Test
    void commands_custom_3() {
        commandParser.processCommand("setseed,1");
        commandParser.processCommand("removeseed");
        commandParser.processCommand("setseed,10");
        commandParser.processCommand("battle,Bulbasaur,Pikachu");
    }

    @Test
    void commands_custom_4() {
        commandParser.processCommand("setseed,20");
        commandParser.processCommand("battle,Pikachu,Charmander");
    }

    @Test
    void commands_custom_5() {
        commandParser.processCommand("setseed,30");
        commandParser.processCommand("battle,Bulbasaur,Squirtle");
    }

    @Test
    void commands_custom_6() {
        commandParser.processCommand("setseed,5");
        commandParser.processCommand("battle,Pikachu,Snorlax");
        commandParser.processCommand("battle,Bulbasaur,Pikachu");

        commandParser.processCommand("setseed,47");
        commandParser.processCommand("battle,Snorlax,Pikachu");

        commandParser.processCommand("removeseed");
        commandParser.processCommand("setseed,287");
        commandParser.processCommand("battle,Snorlax,Squirtle");
    }

    @Test
    void commands_custom_7() {
        commandParser.processCommand("setseed,10");
        commandParser.processCommand("battle,Mew,Snorlax");

        commandParser.processCommand("setseed,67");
        commandParser.processCommand("battle,Mew,Snorlax");

        commandParser.processCommand("removeseed");

        commandParser.processCommand("setseed,77");
        commandParser.processCommand("battle,Geodude,Charmander");
    }

    @Test
    void commands_custom_8() {
        commandParser.processCommand("setseed,54");
        commandParser.processCommand("battle,Ditto,Bulbasaur");
        commandParser.processCommand("removeseed");
        commandParser.processCommand("setseed,67");
        commandParser.processCommand("battle,Ditto,Bulbasaur");
    }

    @Test
    void commands_custom_9() {
        commandParser.processCommand("setseed,1");
        commandParser.processCommand("battle,Ditto,Bulbasaur");

        commandParser.processCommand("setseed,2");
        commandParser.processCommand("battle,Ditto,Bulbasaur");

        commandParser.processCommand("setseed,3");
        commandParser.processCommand("battle,Ditto,Bulbasaur");

        commandParser.processCommand("setseed,4");
        commandParser.processCommand("battle,Ditto,Bulbasaur");

        commandParser.processCommand("setseed,5");
        commandParser.processCommand("battle,Ditto,Bulbasaur");
    }

    @Test
    void commands_custom_10() {
        commandParser.processCommand("setseed,1");
        commandParser.processCommand("battle,Ditto,Bulbasaur");
        commandParser.processCommand("battle,Bulbasaur,Ditto");
        commandParser.processCommand("battle,Pikachu,Charmander");
        commandParser.processCommand("battle,Charmander,Pikachu");
        commandParser.processCommand("battle,Mew,Squirtle");
        commandParser.processCommand("battle,Squirtle,Mew");
        commandParser.processCommand("battle,Geodude,Squirtle");
        commandParser.processCommand("battle,Squirtle,Geodude");
        commandParser.processCommand("battle,Snorlax,Charmander");
        commandParser.processCommand("battle,Charmander,Snorlax");
        commandParser.processCommand("battle,Snorlax,Slowpoke");
    }

    @Test
    void tournamentHappyPath() {
        commandParser.processCommand("setseed,1");
        commandParser.processCommand("tournament,Ditto,Bulbasaur,Pikachu,Charmander");
    }
}
