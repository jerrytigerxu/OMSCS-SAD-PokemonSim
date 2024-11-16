package com.group55.pokemon.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Skill {
    public enum SkillType {
        ATTACK,
        DEFENSE
    }

    private final String name = "skillName";
    private final Integer skillPointsCost = 0;
    private final Integer strength = 0;
    private final SkillType skillType = SkillType.ATTACK;
}
