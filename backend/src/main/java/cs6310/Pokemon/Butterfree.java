package cs6310.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import cs6310.Pokemon.model.domain.Pokemon;
import cs6310.Pokemon.model.domain.Skill;
import cs6310.Pokemon.model.domain.Skill.SkillType;

public class Butterfree extends Pokemon {
    public Butterfree() {
        setStats();
    }

    public Butterfree(int seed) {
        super(seed);
        setStats();
    }

    private void setStats() {
        super.setName("Butterfree");
        super.setActiveDefense(0);
        super.setFullHitPoints(40);
        super.setCurrentHitPoints(40);
        super.setFullSkillPoints(100);
        super.setCurrentSkillPoints(100);

        var attack1 = new Skill("Poison", 0, 0, SkillType.ATTACK);
        var attack2 = new Skill("Gust", 5, 1, SkillType.ATTACK);
        var attack3 = new Skill("Whirlwind", 10, 2, SkillType.ATTACK);
        var attack4 = new Skill("Solar Beam", 30, 6, SkillType.ATTACK);

        super.setAttackSkills(new ArrayList<>(Arrays.asList(attack1, attack2, attack3, attack4)));

        var defense1 = new Skill("Endure", 5, 1, SkillType.DEFENSE);
        var defense2 = new Skill("Block", 10, 2, SkillType.DEFENSE);
        var defense3 = new Skill("Protect", 15, 3, SkillType.DEFENSE);

        super.setDefenseSkills(new ArrayList<>(Arrays.asList(defense1, defense2, defense3)));
    }
}
