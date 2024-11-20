package cs6310.Pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import cs6310.Pokemon.dto.Pokemon;
import cs6310.Pokemon.dto.Skill;
import cs6310.Pokemon.dto.Skill.SkillType;

public class Geodude extends Pokemon {

    public Geodude() {
        setStats();
    }

    public Geodude(long seed) {
        super(seed);
        setStats();
    }

    private void setStats() {
        super.setName("Geodude");
        super.setActiveDefense(0);
        super.setFullHitPoints(25);
        super.setCurrentHitPoints(25);
        super.setFullSkillPoints(100);
        super.setCurrentSkillPoints(100);

        var attack1 = new Skill("Tackle", 0, 1, SkillType.ATTACK);
        var attack2 = new Skill("Rock Throw", 0, 2, SkillType.ATTACK);
        var attack3 = new Skill("Earth Quake", 0, 3, SkillType.ATTACK);
        var attack4 = new Skill("Rock Slide", 0, 6, SkillType.ATTACK);

        super.setAttackSkills(new ArrayList<>(Arrays.asList(attack1, attack2, attack3, attack4)));

        var defense1 = new Skill("Block", 0, 2, SkillType.DEFENSE);
        var defense2 = new Skill("Endure", 0, 1, SkillType.DEFENSE);
        var defense3 = new Skill("Protect", 0, 3, SkillType.DEFENSE);

        super.setDefenseSkills(new ArrayList<>(Arrays.asList(defense2, defense1,defense3)));
    }

}
