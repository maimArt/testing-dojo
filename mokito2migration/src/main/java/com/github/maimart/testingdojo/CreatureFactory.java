package com.github.maimart.testingdojo;

import com.github.maimart.testingdojo.creatures.ButterFly;
import com.github.maimart.testingdojo.creatures.Monster;
import com.github.maimart.testingdojo.creatures.MonsterLevel;

public interface CreatureFactory {
    Monster createMonster(MonsterLevel weak);

    ButterFly createASweetLittleButterfly();
}
