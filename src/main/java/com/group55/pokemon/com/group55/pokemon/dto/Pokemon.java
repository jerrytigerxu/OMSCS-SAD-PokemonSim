package com.group55.pokemon.dto;

import java.util.List;
import java.util.Random;

import com.group55.pokemon.dto.Skill.SkillType;

public interface Pokemon {
    String getName();
    Integer getCurrentHitPoints();
    Integer getMaxHitPoints();
    Integer getCurrentSkillPoints();
    Integer getMaxSkillPoints();
    List<Skill> getAttackSkills();
    List<Skill> getDefenseSkills();
    Integer getActiveDefense();
    Random getRandom();

    SkillType calculateMoveType();
    void updateHealth();
    Skill selectAttackSkill();
    Skill selectDefenseSkill();
}