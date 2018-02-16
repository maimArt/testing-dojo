package com.github.maimart.monsterhunterfx;

import com.github.maimart.monsterhunterfx.monsters.Monster;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.event.EventHandler;

public class HeroControlPresenter {

    private final MonsterHunterModel model;
    private final Service<Monster> monsterService;

    public HeroControlPresenter(MonsterHunterModel model, Service<Monster> rentAMonsterService) {
        this.model = model;
        this.monsterService = rentAMonsterService;
        initMonsterService();
    }

    private void initMonsterService() {
        monsterService.setOnSucceeded(EventHandler -> model.setMonster(monsterService.getValue()));
    }

    public void seekAMonsterButtonPressed() {
        monsterService.start();
    }
}
