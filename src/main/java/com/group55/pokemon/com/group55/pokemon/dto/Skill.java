package com.group55.pokemon.dto;

public class Skill {
    enum SkillType {
        ATTACK,
        DEFENSE
    }
    Integer skillPointsCost = 0;
    SkillType skillType = SkillType.ATTACK;
}
