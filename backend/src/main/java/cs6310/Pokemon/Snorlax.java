package cs6310.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import cs6310.Pokemon.dto.Pokemon;
import cs6310.Pokemon.dto.Skill;
import cs6310.Pokemon.dto.Skill.SkillType;

public class Snorlax extends Pokemon {

    public Snorlax() {
        setStats();
    }

    public Snorlax(int seed) {
        super(seed);
        setStats();
    }

    private void setStats() {
        super.setName("Snorlax");
        super.setActiveDefense(0);
        super.setFullHitPoints(40);
        super.setCurrentHitPoints(40);
        super.setFullSkillPoints(100);
        super.setCurrentSkillPoints(100);

        var attack1 = new Skill("Rest", 0, 0, SkillType.ATTACK);
        var attack2 = new Skill("Snore", 0, 6, SkillType.ATTACK);

        super.setAttackSkills(new ArrayList<>(Arrays.asList(attack1, attack2)));

        var defense1 = new Skill("Block", 0, 2, SkillType.DEFENSE);
        var defense2 = new Skill("Endure", 0, 1, SkillType.DEFENSE);
        var defense3 = new Skill("Protect", 0, 3, SkillType.DEFENSE);

        super.setDefenseSkills(new ArrayList<>(Arrays.asList(defense2, defense1,defense3)));
    }

}
