package cs6310.Pokemon.dto;

import lombok.Data;

@Data
public class Skill {
    public enum SkillType {
        ATTACK,
        DEFENSE
    }

    public Skill(String name, int skillPointsCost, int strength, SkillType skillType) {
        this.name = name;
        this.skillPointsCost = skillPointsCost;
        this.strength = strength;
        this.skillType = skillType;
    }

    private String name = "skillName";
    private int skillPointsCost = 0;
    private int strength = 0;
    private SkillType skillType;
}