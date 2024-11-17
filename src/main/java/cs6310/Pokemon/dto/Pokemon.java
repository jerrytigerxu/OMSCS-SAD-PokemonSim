package cs6310.Pokemon.dto;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

import cs6310.Pokemon.exceptions.BattleLostException;
import lombok.Data;

@Data
public abstract class Pokemon {
    private String name;
    private int currentHitPoints;
    private int fullHitPoints;
    private int currentSkillPoints;
    private int fullSkillPoints;
    private List<Skill> attackSkills;
    private List<Skill> defenseSkills;
    private int activeDefense;
    private Random rand;

    public Pokemon() {
    }

    public Pokemon(int seed) {
        this.rand = new Random(seed);
    }

    public void battle(Object opponent, int incomingDamage) throws BattleLostException {
        this.currentHitPoints -= incomingDamage;
        if (this.currentHitPoints <= 0) {
            throw new BattleLostException(this.name + " has lost");
        }

        var hitPointsRatio = this.currentHitPoints / this.fullHitPoints;
        var attackChance = getAttackChance(hitPointsRatio);
        var randNum = this.rand.nextInt(10);

        try {
            if (randNum < attackChance) {
                var attackSkill = this.attackSkills.get(this.rand.nextInt(this.attackSkills.size()));
                var opponentBattleMethod = opponent.getClass().getMethod("battle", Object.class, int.class);
                opponentBattleMethod.invoke(opponent, (Object)this, attackSkill.getStrength());
            } else {
                var defenseSkill = this.defenseSkills.get(this.rand.nextInt(this.defenseSkills.size()));
                this.activeDefense = defenseSkill.getStrength();

                var opponentBattleMethod = opponent.getClass().getMethod("battle", Object.class, int.class);
                opponentBattleMethod.invoke(opponent, this, 0);
            }
        } catch (InvocationTargetException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private int getAttackChance(int hitPointsRatio) {
        var attackChance = 0;
        if (hitPointsRatio > 0.7) {
            attackChance = 8;
        } else if (hitPointsRatio < 0.7 && hitPointsRatio >= 0.3) {
            attackChance = 5;
        } else {
            attackChance = 3;
        }

        return attackChance;
    }

    @Override
    public String toString() {
        return "Pokemon: " + this.name + " has " + this.currentHitPoints + " hp"
                + "\nAttack Skills: " + this.attackSkills + "\nDefense Skills: " + this.defenseSkills;
        // Example output:
        // Pokemon: Butterfree has 40 hp
        // Attack Skills:
        // Name: Poison Damage: 0
        // Damage Over Time: 2
        // Damage Turn Count: 3
        // Name: Gust Damage: 1
        // Name: Whirlwind Damage: 2
        // Name: Solar Beam Damage: 6
        // Defense Skills:
        // Name: Endure Defense: 1
        // Name: Block Defense: 2
        // Name: Protect Defense: 3
    }
}