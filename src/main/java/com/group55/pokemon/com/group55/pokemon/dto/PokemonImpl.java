package com.group55.pokemon.dto;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.group55.pokemon.dto.Skill.SkillType;

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

    public Integer getActiveDefense() {
        return activeDefense;
    }

    public void setActiveDefense(Integer activeDefense) {
        this.activeDefense = activeDefense;
    }

    @Override
    public SkillType calculateMoveType() {
        return random.nextBoolean() ? SkillType.ATTACK : SkillType.DEFENSE;
    }

    @Override
    public void updateHealth(int update) {
        this.currentHitPoints += update;
    }

    @Override
    public Skill selectAttackSkill() {
        return attackSkills.get(random.nextInt(attackSkills.size()));
    }

    @Override
    public Skill selectDefenseSkill() {
        return defenseSkills.get(random.nextInt(defenseSkills.size()));
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }

    @Override
    public Integer getCurrentHitPoints() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentHitPoints'");
    }

    @Override
    public Integer getMaxHitPoints() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMaxHitPoints'");
    }

    @Override
    public Integer getCurrentSkillPoints() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentSkillPoints'");
    }

    @Override
    public Integer getMaxSkillPoints() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMaxSkillPoints'");
    }

    @Override
    public List<Skill> getAttackSkills() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAttackSkills'");
    }

    @Override
    public List<Skill> getDefenseSkills() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDefenseSkills'");
    }

    @Override
    public Random getRandom() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRandom'");
    }

}
