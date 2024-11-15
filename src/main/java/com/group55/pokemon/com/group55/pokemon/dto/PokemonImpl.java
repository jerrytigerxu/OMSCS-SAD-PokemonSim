package com.group55.pokemon.dto;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.group55.pokemon.dto.Skill.SkillType;

import lombok.Data;

@Data
public class PokemonImpl implements Pokemon {
    private String name = "DefaultName";
    private Integer currentHitPoints = 100;
    private Integer maxHitPoints = 100;
    private Integer currentSkillPoints = 100;
    private Integer maxSkillPoints = 100;
    private List<Skill> attackSkills = Collections.emptyList();
    private List<Skill> defenseSkills = Collections.emptyList();
    private Integer activeDefense = 0;
    private Random random = new Random();

    public PokemonImpl(String name) {
        this.name = name;
    }

    @Override
    public SkillType calculateMoveType() {
        return random.nextBoolean() ? SkillType.ATTACK : SkillType.DEFENSE;
    }

    @Override
    public void updateHealth() {
        // TODO Auto-generated method stub
    }

    @Override
    public Skill selectAttackSkill() {
        return attackSkills.get(random.nextInt(attackSkills.size()));
    }

    @Override
    public Skill selectDefenseSkill() {
        return defenseSkills.get(random.nextInt(defenseSkills.size()));
    }

}
