package com.github.maimart.monsterhunterfx;

import com.github.maimart.monsterhunterfx.monsters.Monster;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MonsterHunterModel {

    private final ObjectProperty<Monster> monster = new SimpleObjectProperty<>();

    public Monster getMonster() {
        return monster.get();
    }

    public void setMonster(Monster monster) {
        this.monster.set(monster);
    }

    public ObjectProperty<Monster> monsterProperty() {
        return monster;
    }
}
