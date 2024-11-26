package cs6310.Pokemon.dto;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import cs6310.Pokemon.Ditto;
import cs6310.Pokemon.dto.Skill.SkillType;
import lombok.Data;

import cs6310.Pokemon.dto.Result;

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

    public void battle(Object opponent, int incomingDamage, boolean isFirstAttack, Result result) {
        var originalIncomingDamage = incomingDamage;
        boolean isAttack = incomingDamage > 0;
        if (this.activeDefense > 0 && incomingDamage > 0) {
            var string = this.name + " successfully reduced " + opponent.getClass().getSimpleName()
                    + "'s damage by " + this.activeDefense + " with " + activeDefenseSkill.getName();
            //result.orderOfBattle += string + ",";
            result.getOrderOfBattle().add(string);
            System.out.println(string);
            incomingDamage = Math.max(0, incomingDamage - this.activeDefense);
        }
        this.currentHitPoints = Math.max(currentHitPoints - incomingDamage, 0);

        if (!isFirstAttack && isAttack) {
            var string = this.name + " has received " + incomingDamage + " dmg, remaining hp is "
                    + this.currentHitPoints;
            System.out.println(string);
            //result.orderOfBattle += string + ",";
            result.getOrderOfBattle().add(string);
        }

        if (this.currentHitPoints <= 0) {
            return;
        }

        this.activeDefense = 0;
        this.activeDefenseSkill = null;

        // --- suspect issue below

        Double hitPointsRatio = (double) this.currentHitPoints / (double) this.fullHitPoints;
        int randNum = this.rand.nextInt(10);
        boolean isAttacking = false;

        if (hitPointsRatio >= 0.7) { // 80 %
            if (randNum >= 2) {
                isAttacking = true;
            } else {
                isAttacking = false;
            }
        } else if (hitPointsRatio >= 0.3) {
            if (randNum >= 5) { // 50%
                isAttacking = true;
            } else {
                isAttacking = false;
            }
        } else {
            if (randNum >= 7) { // 30%
                isAttacking = true;
            } else {
                isAttacking = false;
            }
        }

        // --- issue above

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

                var string = this.name + " is attacking with " + attackSkill.getName() + " for "
                        + attackSkill.getStrength() + " damage to " + opponent.getClass().getSimpleName();
                System.out.println(string);
                //result.orderOfBattle += string + ",";
                result.getOrderOfBattle().add(string);

                var opponentBattleMethod = opponent.getClass().getMethod("battle", Object.class, int.class,
                        boolean.class,Result.class);
                opponentBattleMethod.invoke(opponent, (Object) this, attackSkill.getStrength(), false,result);
            } else {
                var defenseSkill = this.defenseSkills.get(this.rand.nextInt(this.defenseSkills.size()));
                
                var string = this.name + " is attempting to defend with " + defenseSkill.getName();
                System.out.println(string);
                //result.orderOfBattle += string + ",";
                result.getOrderOfBattle().add(string);

                this.activeDefense = defenseSkill.getStrength();
                this.activeDefenseSkill = defenseSkill;

                var opponentBattleMethod = opponent.getClass().getMethod("battle", Object.class, int.class,
                        boolean.class,Result.class);
                opponentBattleMethod.invoke(opponent, (Object) this, 0, false,result);
            }
        } catch (InvocationTargetException | NoSuchMethodException | SecurityException | IllegalAccessException
                | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String attackSkillsFormatted = "";
        String defenseSkillsFormatted = "";
        for (var skill : this.attackSkills.stream().sorted(Comparator.comparingInt(Skill::getStrength))
                .toList()) {
            attackSkillsFormatted = attackSkillsFormatted
                    .concat("\nName: " + skill.getName() + " Damage: " + skill.getStrength());
        }
        for (var skill : this.defenseSkills.stream().sorted(Comparator.comparingInt(Skill::getStrength))
                .toList()) {
            defenseSkillsFormatted = defenseSkillsFormatted
                    .concat("\nName: " + skill.getName() + " Defense: " + skill.getStrength());
        }

        return "Pokemon: " + this.name + " has " + this.currentHitPoints + " hp"
                + "\nAttack Skills:" + attackSkillsFormatted.toString() + "\nDefense Skills:"
                + defenseSkillsFormatted;
    }
}