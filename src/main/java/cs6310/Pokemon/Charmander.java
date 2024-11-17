package cs6310.Pokemon;

import java.util.List;

import cs6310.Pokemon.dto.Pokemon;
import cs6310.Pokemon.dto.Skill;
import cs6310.Pokemon.dto.Skill.SkillType;

public class Charmander extends Pokemon {

    public Charmander() {
        setStats();
    }

    public Charmander(int seed) {
        super(seed);
        setStats();
    }

    private void setStats() {
        super.setName("Charmander");
        super.setActiveDefense(0);
        super.setFullHitPoints(100);
        super.setCurrentHitPoints(100);
        super.setFullSkillPoints(100);
        super.setCurrentSkillPoints(100);

        super.setAttackSkills(List.of(new Skill("Attack", 0, 1, SkillType.ATTACK),
                new Skill("Scratch", 0, 2, SkillType.ATTACK), new Skill("Ember", 0, 3, SkillType.ATTACK)));

        super.setDefenseSkills(
                List.of(new Skill("Block", 0, 2, SkillType.DEFENSE), new Skill("Endure", 0, 1, SkillType.DEFENSE)));
    }

    
}
