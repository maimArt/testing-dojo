package com.github.maimart.monsterhunterfx;

import com.github.maimart.monsterhunterfx.monsters.Monster;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import static javafx.beans.binding.Bindings.selectInteger;

public class MonsterHunterPresenter {

    private final StringProperty statusText = new SimpleStringProperty();
    private final BooleanProperty seekingPossible = new SimpleBooleanProperty();
    private final BooleanProperty slayingPossible = new SimpleBooleanProperty();
    private final BooleanProperty monstersHealthShown = new SimpleBooleanProperty();
    private final DoubleProperty monstersHealthProgress = new SimpleDoubleProperty();

    private final MonsterHunterModel model;
    private final MonsterHunt controller;

    public MonsterHunterPresenter(MonsterHunterModel model, MonsterHunt controller) {
        this.model = model;
        this.controller = controller;
        initViewBindings();
    }

    private void initViewBindings() {
        IntegerBinding monsterHealthPoints = selectInteger(model.monsterProperty(), "healthPoints");

        statusText.bind(Bindings.createObjectBinding(() -> model.getHuntingState().getMessage(), model.huntingStateProperty()));
        seekingPossible.bind(model.monsterProperty().isNull().or(monsterHealthPoints.lessThanOrEqualTo(0)));
        slayingPossible.bind(model.monsterProperty().isNotNull().and(monsterHealthPoints.greaterThan(0)));
        monstersHealthShown.bind(model.monsterProperty().isNotNull());
        monstersHealthProgress.bind(Bindings.createDoubleBinding(() -> model.getMonster() != null ? (double) model.getMonster().getHealthPoints() / Monster.STARTING_HEALTHPOINTS : -1.0, monsterHealthPoints));
    }

    void seek() {
        controller.seekAMonster();
    }

    void slay() {
        controller.slayTheMonster();
    }

    String getStatusText() {
        return statusText.get();
    }

    StringProperty statusTextProperty() {
        return statusText;
    }

    boolean isSeekingPossible() {
        return seekingPossible.get();
    }

    BooleanProperty seekingPossibleProperty() {
        return seekingPossible;
    }

    boolean isSlayingPossible() {
        return slayingPossible.get();
    }

    BooleanProperty slayingPossibleProperty() {
        return slayingPossible;
    }

    public boolean isMonstersHealthShown() {
        return monstersHealthShown.get();
    }

    public BooleanProperty monstersHealthShownProperty() {
        return monstersHealthShown;
    }

    double getMonstersHealthProgress() {
        return monstersHealthProgress.get();
    }

    DoubleProperty monstersHealthProgressProperty() {
        return monstersHealthProgress;
    }
}
