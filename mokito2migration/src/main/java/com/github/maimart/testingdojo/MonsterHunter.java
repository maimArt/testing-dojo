package com.github.maimart.testingdojo;

import com.github.maimart.testingdojo.creatures.Monster;
import com.github.maimart.testingdojo.creatures.MonsterLevel;

public class MonsterHunter {
    private final CreatureFactory creatureFactory;

    public MonsterHunter(CreatureFactory creatureFactory) {
        this.creatureFactory = creatureFactory;
    }

    public Monster seekAMonster(MonsterLevel weak) {
        return creatureFactory.createMonster(weak);
    }
}
