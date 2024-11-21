package cs6310.Pokemon.dto;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

import cs6310.Pokemon.Ditto;
import cs6310.Pokemon.dto.Skill.SkillType;
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
    private Skill activeDefenseSkill;
    private Random rand;

    public Pokemon() {
    }

    public Pokemon(int seed) {
        this.rand = new Random(seed);
    }

    public void battle(Object opponent, int incomingDamage, boolean isFirstAttack) {
        var originalIncomingDamage = incomingDamage;
        boolean isAttack = incomingDamage > 0;
        if (this.activeDefense > 0 && incomingDamage > 0) {
            System.out.println(this.name + " successfully reduced " + opponent.getClass().getSimpleName()
                    + "'s damage by " + this.activeDefense + " with " + activeDefenseSkill.getName());
            incomingDamage = Math.max(0, incomingDamage - this.activeDefense);
        }
        this.currentHitPoints = Math.max(currentHitPoints - incomingDamage, 0);

        if (!isFirstAttack && isAttack)
            System.out.println(
                    this.name + " has received " + incomingDamage + " dmg, remaining hp is " + this.currentHitPoints);
        if (this.currentHitPoints <= 0) {
            return;
        }

        this.activeDefense = 0;
        this.activeDefenseSkill = null;

        Double hitPointsRatio = (double) this.currentHitPoints / (double) this.fullHitPoints;
        int randNum = this.rand.nextInt(10);
        boolean isAttacking = false;

        if (hitPointsRatio >= 0.7) {
            if (randNum >= 2) {
                isAttacking = true;
            } else {
                isAttacking = false;
            }
        } else if (hitPointsRatio >= 0.3) {
            if (randNum >= 5) {
                isAttacking = true;
            } else {
                isAttacking = false;
            }
        } else {
            if (randNum >= 7) {
                isAttacking = true;
            } else {
                isAttacking = false;
            }
        }

        try {
            if (isAttacking) {
                var attackSkill = this.attackSkills.get(this.rand.nextInt(this.attackSkills.size()));

                if (this.name.equals("Ditto")) {
                    if (((Ditto) this).isTransformEnabled()) {
                        attackSkill = new Skill("Attack", 0, originalIncomingDamage, SkillType.ATTACK);
                        ((Ditto) this).setTransformEnabled(false);
                    } else {
                        ((Ditto) this).setTransformEnabled(true);
                    }
                }

                System.out.println(this.name + " is attacking with " + attackSkill.getName() + " for "
                        + attackSkill.getStrength() + " damage to " + opponent.getClass().getSimpleName());

                var opponentBattleMethod = opponent.getClass().getMethod("battle", Object.class, int.class,
                        boolean.class);
                opponentBattleMethod.invoke(opponent, (Object) this, attackSkill.getStrength(), false);
            } else {
                var defenseSkill = this.defenseSkills.get(this.rand.nextInt(this.defenseSkills.size()));
                System.out.println(this.name + " is attempting to defend with " + defenseSkill.getName());
                this.activeDefense = defenseSkill.getStrength();
                this.activeDefenseSkill = defenseSkill;

                var opponentBattleMethod = opponent.getClass().getMethod("battle", Object.class, int.class,
                        boolean.class);
                opponentBattleMethod.invoke(opponent, (Object) this, 0, false);
            }
        } catch (InvocationTargetException | NoSuchMethodException | SecurityException | IllegalAccessException
                | IllegalArgumentException e) {
            e.printStackTrace();
        }
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