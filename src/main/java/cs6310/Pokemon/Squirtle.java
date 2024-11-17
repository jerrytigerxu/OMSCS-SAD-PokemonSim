package cs6310.Pokemon;

import java.util.List;

import cs6310.Pokemon.dto.Pokemon;
import cs6310.Pokemon.dto.Skill;
import cs6310.Pokemon.dto.Skill.SkillType;

public class Squirtle extends Pokemon {
    public Squirtle() {
        setStats();
    }

    public Squirtle(int seed) {
        super(seed);
        setStats();
    }

    private void setStats() {
        super.setName("Squirtle");
        super.setActiveDefense(0);
        super.setFullHitPoints(100);
        super.setCurrentHitPoints(100);
        super.setFullSkillPoints(100);
        super.setCurrentSkillPoints(100);

        super.setAttackSkills(List.of(new Skill("Tackle", 0, 2, SkillType.ATTACK),
                new Skill("Hydro Pump", 0, 6, SkillType.ATTACK), new Skill("Attack", 0, 1, SkillType.ATTACK)));

        super.setDefenseSkills(
                List.of(new Skill("Block", 0, 2, SkillType.DEFENSE), new Skill("Protect", 0, 3, SkillType.DEFENSE)));
    }
}
