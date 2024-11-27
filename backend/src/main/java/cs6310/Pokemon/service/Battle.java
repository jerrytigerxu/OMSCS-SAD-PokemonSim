package cs6310.Pokemon.service;

import java.lang.reflect.InvocationTargetException;
import org.springframework.stereotype.Service;
import cs6310.Pokemon.dto.Pokemon;
import cs6310.Pokemon.dto.BattleResult;

import lombok.Data;

@Data
@Service
public class Battle {
    private Pokemon pokemonOne;
    private Pokemon pokemonTwo;
    private int seed;

    public BattleResult startBattle(String pokemonOne, String pokemonTwo) {
        Class<?> pokemon1 = null;
        Class<?> pokemon2 = null;

        try {
            pokemon1 = Class.forName("cs6310.Pokemon." + pokemonOne);
            pokemon2 = Class.forName("cs6310.Pokemon." + pokemonTwo);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (pokemon1 == null || pokemon2 == null) {
            return new BattleResult();
        }

        try {
            this.pokemonOne = (Pokemon) pokemon1.getDeclaredConstructor(int.class).newInstance(seed);
            this.pokemonTwo = (Pokemon) pokemon2.getDeclaredConstructor(int.class).newInstance(seed + 1);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }

        var result = new BattleResult();
        this.pokemonOne.battle(this.pokemonTwo, 0, true, result);
        var loser = this.pokemonOne.getCurrentHitPoints() <= 0 ? this.pokemonOne.getName() : this.pokemonTwo.getName();
        if (this.pokemonOne.getName().equals(loser)) {
            System.out.println(this.pokemonOne.getName() + " has lost");
            System.out.println(this.pokemonTwo.getName() + " has won the battle");
            result.setWinnerPokemon(pokemonTwo);
            result.setLoserPokemon(pokemonOne);
        } else {
            System.out.println(this.pokemonTwo.getName() + " has lost");
            System.out.println(this.pokemonOne.getName() + " has won the battle");
            result.setWinnerPokemon(pokemonOne);
            result.setLoserPokemon(pokemonTwo);
        }

        return result;
    }

}
