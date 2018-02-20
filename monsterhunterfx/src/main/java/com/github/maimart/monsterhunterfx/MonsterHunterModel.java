package com.github.maimart.monsterhunterfx;

import com.github.maimart.monsterhunterfx.monsters.Monster;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MonsterHunterModel {

    private final ObjectProperty<Monster> monster = new SimpleObjectProperty<>();
    private final ObjectProperty<HuntingState> huntingState = new SimpleObjectProperty<>(HuntingState.IDLING_AROUND);

    public Monster getMonster() {
        return monster.get();
    }

    public void setMonster(Monster monster) {
        this.monster.set(monster);
    }

    public ObjectProperty<Monster> monsterProperty() {
        return monster;
    }

    public HuntingState getHuntingState() {
        return huntingState.get();
    }

    public ObjectProperty<HuntingState> huntingStateProperty() {
        return huntingState;
    }

    public void setHuntingState(HuntingState huntingState) {
        this.huntingState.set(huntingState);
    }
}
