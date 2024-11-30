package cs6310.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import cs6310.Pokemon.model.domain.Pokemon;
import cs6310.Pokemon.model.domain.Skill;
import cs6310.Pokemon.model.domain.Skill.SkillType;

public class Mew extends Pokemon {

    public Mew() {
        setStats();
    }

    public Mew(int seed) {
        super(seed);
        setStats();
    }

    private void setStats() {
        super.setName("Mew");
        super.setActiveDefense(0);
        super.setFullHitPoints(25);
        super.setCurrentHitPoints(25);
        super.setFullSkillPoints(100);
        super.setCurrentSkillPoints(100);

        var attack1 = new Skill("Pound", 0, 1, SkillType.ATTACK);
        var attack2 = new Skill("Imprison", 0, 2, SkillType.ATTACK);
        var attack3 = new Skill("Charm", 0, 0, SkillType.ATTACK);
        var attack4 = new Skill("Psychic", 0, 15, SkillType.ATTACK);

        super.addAttackSkill(attack3);
        super.addAttackSkill(attack1);
        super.addAttackSkill(attack2);
        super.addAttackSkill(attack4);

        var defense1 = new Skill("Block", 0, 2, SkillType.DEFENSE);
        var defense2 = new Skill("Endure", 0, 1, SkillType.DEFENSE);
        var defense3 = new Skill("Protect", 0, 3, SkillType.DEFENSE);

        super.addDefenseSkill(defense2);
        super.addDefenseSkill(defense1);
        super.addDefenseSkill(defense3);
    }

}
