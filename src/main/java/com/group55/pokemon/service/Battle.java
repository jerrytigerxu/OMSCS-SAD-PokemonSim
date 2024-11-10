package com.group55.pokemon.service;

import com.group55.pokemon.dto.Pokemon;
import com.group55.pokemon.dto.Result;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Battle {
    enum BattleStatus {
        NOT_STARTED,
        CONTINUE,
        FINISHED
    }
    private final Pokemon pokemonOne;
    private final Pokemon pokemonTwo;
    BattleStatus status;
    private final Integer seed;

    public Result startBattle() {
        if(pokemonOne == null || pokemonTwo == null) {
           System.out.println("Battle cannot start without two Pokemon");
            return null;
        }
        if(seed == null) {
            System.out.println("Seed can not be null.");
            return null;
        }

        status = BattleStatus.CONTINUE;
        while(status == BattleStatus.CONTINUE) {
            runTurn();
            status = checkStatus();
        }
    }

    void runTurn() {


        if(pokemonOne.getCurrentHitPoints() <= 0)
            status = BattleStatus.FINISHED;
            return new Result();
        }
    }

    BattleStatus checkStatus() {

    }
}
