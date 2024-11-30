package cs6310.Pokemon.utility;

import java.time.LocalDate;

import cs6310.Pokemon.model.dto.BattleResult;
import cs6310.Pokemon.model.entity.BattleResultEntity;

public class MapperUtil {
    public static BattleResultEntity mapBattleResultDtoToEntity(BattleResult result) {
        BattleResultEntity battleResultEntity = new BattleResultEntity();
        battleResultEntity.setId(LocalDate.now().toEpochDay());
        battleResultEntity.setWinnerPokemon(result.getWinnerPokemon());
        battleResultEntity.setLoserPokemon(result.getLoserPokemon());
        return battleResultEntity;
    }
}
