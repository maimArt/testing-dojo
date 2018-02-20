package com.github.maimart.monsterhunterfx.monsters;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Monster {
    public static final int STARTING_HEALTHPOINTS = 3;
    private final IntegerProperty healthPoints = new SimpleIntegerProperty(STARTING_HEALTHPOINTS);

    public int getHealthPoints() {
        return healthPoints.get();
    }

    public IntegerProperty healthPointsProperty() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints.set(healthPoints);
    }
}
