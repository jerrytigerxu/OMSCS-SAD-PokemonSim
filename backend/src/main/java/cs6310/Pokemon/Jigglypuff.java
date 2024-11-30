package cs6310.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import cs6310.Pokemon.model.domain.Pokemon;
import cs6310.Pokemon.model.domain.Skill;
import cs6310.Pokemon.model.domain.Skill.SkillType;

public class Jigglypuff extends Pokemon {
    public Jigglypuff() {
        setStats();
    }

    public Jigglypuff(int seed) {
        super(seed);
        setStats();
    }

    private void setStats() {
        super.setName("Jigglypuff");
        super.setActiveDefense(0);
        super.setFullHitPoints(25);
        super.setCurrentHitPoints(25);
        super.setFullSkillPoints(100);
        super.setCurrentSkillPoints(100);

        var attack1 = new Skill("Sharpie", 0, 0, SkillType.ATTACK);
        var attack2 = new Skill("Sing", 5, 1, SkillType.ATTACK);
        var attack3 = new Skill("Pound", 10, 2, SkillType.ATTACK);
        var attack4 = new Skill("Double Slap", 50, 10, SkillType.ATTACK);

        super.addAttackSkill(attack1);
        super.addAttackSkill(attack2);
        super.addAttackSkill(attack3);
        super.addAttackSkill(attack4);

        var defense1 = new Skill("Endure", 5, 1, SkillType.DEFENSE);
        var defense2 = new Skill("Block", 10, 2, SkillType.DEFENSE);
        var defense3 = new Skill("Protect", 15, 3, SkillType.DEFENSE);

        super.addDefenseSkill(defense1);
        super.addDefenseSkill(defense2);
        super.addDefenseSkill(defense3);
    }
}
