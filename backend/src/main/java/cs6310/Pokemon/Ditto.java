package cs6310.Pokemon;

import cs6310.Pokemon.model.domain.Pokemon;
import cs6310.Pokemon.model.domain.Skill;
import cs6310.Pokemon.model.domain.Skill.SkillType;
import lombok.Getter;
import lombok.Setter;

public class Ditto extends Pokemon {

    @Getter
    @Setter
    private boolean transformEnabled = false;

    public Ditto() {
        setStats();
    }

    public Ditto(int seed) {
        super(seed);
        setStats();
    }

    private void setStats() {
        super.setName("Ditto");
        super.setActiveDefense(0);
        super.setFullHitPoints(35);
        super.setCurrentHitPoints(35);
        super.setFullSkillPoints(100);
        super.setCurrentSkillPoints(100);

        var attack1 = new Skill("Transform", 0, 0, SkillType.ATTACK);

        super.addAttackSkill(attack1);

        var defense1 = new Skill("Block", 10, 2, SkillType.DEFENSE);
        var defense2 = new Skill("Endure", 5, 1, SkillType.DEFENSE);
        var defense3 = new Skill("Protect", 15, 3, SkillType.DEFENSE);

        super.addDefenseSkill(defense2);
        super.addDefenseSkill(defense1);
        super.addDefenseSkill(defense3);
    }

}
