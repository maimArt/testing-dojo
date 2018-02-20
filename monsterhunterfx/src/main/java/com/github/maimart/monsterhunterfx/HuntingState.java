package com.github.maimart.monsterhunterfx;

public enum HuntingState {
    IDLING_AROUND("Just idling around"), MONSTER_DEFEATED("Hunting done! Victory!"), MONSTER_PRESENT("A monster is attacking!");

    private final String message;

    HuntingState(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
