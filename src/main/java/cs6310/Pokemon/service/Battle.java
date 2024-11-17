package cs6310.Pokemon.service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import cs6310.Pokemon.dto.Skill;
import cs6310.Pokemon.exceptions.BattleLostException;
import cs6310.Pokemon.dto.Pokemon;
import cs6310.Pokemon.dto.Result;

import lombok.Data;

@Data
@Service
public class Battle {
    private Pokemon pokemonOne;
    private Pokemon pokemonTwo;
    private Integer seed;

    public Result startBattle(String pokemonOne, String pokemonTwo) {
        Class<?> pokemon1 = null;
        Class<?> pokemon2 = null;

        try {
            pokemon1 = Class.forName("cs6310.Pokemon." + pokemonOne);
            pokemon2 = Class.forName("cs6310.Pokemon." + pokemonTwo);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(pokemon1 == null || pokemon2 == null) {
            return new Result();
        }

        try {
            this.pokemonOne = (Pokemon) pokemon1.getDeclaredConstructor(int.class).newInstance(seed);
            this.pokemonTwo = (Pokemon) pokemon2.getDeclaredConstructor(int.class).newInstance(seed);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }

        try {
            this.pokemonOne.battle(this.pokemonTwo, 0);

        } catch (BattleLostException battleLostException) {
            var loser = battleLostException.getLosingPokemonName();
            if(this.pokemonOne.getName().equals(loser)) {
                System.out.println(this.pokemonTwo.getName() + " has won");
                return new Result(this.pokemonTwo,this.pokemonOne);
            } else {
                System.out.println(this.pokemonOne.getName() + " has won");
                return new Result(this.pokemonOne,this.pokemonTwo);
            }            
        }
        return new Result();
    }
}
