package cs6310.Pokemon.service;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cs6310.Pokemon.model.domain.Pokemon;
import cs6310.Pokemon.model.dto.BattleResult;
import cs6310.Pokemon.model.entity.BattleResultEntity;
import cs6310.Pokemon.repository.BattleResultRepository;
import lombok.Data;
import cs6310.Pokemon.utility.CustomPrintStream;
import cs6310.Pokemon.utility.MapperUtil;

@Data
@Service
public class Battle {
    private Pokemon pokemonOne;
    private Pokemon pokemonTwo;
    private int seed;

    @Autowired
    private BattleResultRepository battleResultRepository;

    public BattleResult startBattle(String pokemonOne, String pokemonTwo) {
        Class<?> pokemon1 = null;
        Class<?> pokemon2 = null;
        var result = new BattleResult();

        try {
            pokemon1 = Class.forName("cs6310.Pokemon." + pokemonOne);
        } catch (ClassNotFoundException e) {
            System.out.println("Name: " + pokemonOne + " was invalid and has forfeited the battle");
            System.out.println(pokemonOne + " has lost");
            System.out.println(pokemonTwo + " has won the battle");
            result.setWinnerPokemon(pokemonTwo);
            result.setLoserPokemon(pokemonOne);
            return result;
        }
        try {
            pokemon2 = Class.forName("cs6310.Pokemon." + pokemonTwo);
        } catch (ClassNotFoundException e) {
            System.out.println("Name: " + pokemonTwo + " was invalid and has forfeited the battle");
            System.out.println(pokemonTwo + " has lost");
            System.out.println(pokemonOne + " has won the battle");
            result.setWinnerPokemon(pokemonOne);
            result.setLoserPokemon(pokemonTwo);
            return result;
        }

        if (pokemon1 == null && pokemon2 == null) {
            return new BattleResult();
        }

        try {
            this.pokemonOne = (Pokemon) pokemon1.getDeclaredConstructor(int.class).newInstance(seed);
            this.pokemonTwo = (Pokemon) pokemon2.getDeclaredConstructor(int.class).newInstance(seed + 1);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }

        CustomPrintStream customPrintStream = new CustomPrintStream(System.out);
        PrintStream originalOut = System.out;
        System.setOut(customPrintStream);

        try {
            this.pokemonOne.battle(this.pokemonTwo, 0);
        } finally {
            System.setOut(originalOut);
        }

        result.setOrderOfBattle(customPrintStream.getOutputList());

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

        BattleResultEntity battleResultEntity = MapperUtil.mapBattleResultDtoToEntity(result);

        battleResultRepository.save(battleResultEntity);

        return result;
    }

}
