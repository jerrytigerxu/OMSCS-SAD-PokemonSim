package cs6310.Pokemon.utility;

import cs6310.Pokemon.model.dto.BattleResult;
import cs6310.Pokemon.model.entity.BattleResultEntity;

public class MapperUtil {
    public static BattleResultEntity mapBattleResultDtoToEntity(BattleResult result) {
        BattleResultEntity battleResultEntity = new BattleResultEntity();
        battleResultEntity.setWinnerPokemon(result.getWinnerPokemon());
        battleResultEntity.setLoserPokemon(result.getLoserPokemon());
        return battleResultEntity;
    }
}
