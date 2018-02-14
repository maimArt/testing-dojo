package com.github.maimart.testingdojo.creatures;

public class Monster {

    private final MonsterLevel level;

    public Monster(MonsterLevel level) {
        this.level = level;
    }

    public MonsterLevel getLevel() {
        return level;
    }
}
