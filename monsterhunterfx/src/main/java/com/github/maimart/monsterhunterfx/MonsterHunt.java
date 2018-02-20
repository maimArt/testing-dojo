package com.github.maimart.monsterhunterfx;

import com.github.maimart.monsterhunterfx.monsters.Monster;
import com.github.maimart.monsterhunterfx.monsters.MonsterService;

public class MonsterHunt {

    private final MonsterHunterModel monsterHunterModel;
    private final MonsterService monsterService;

    public MonsterHunt(MonsterHunterModel monsterHunterModel, MonsterService monsterService) {
        this.monsterHunterModel = monsterHunterModel;
        this.monsterService = monsterService;
        monsterHunterModel.setHuntingState(HuntingState.IDLING_AROUND);
    }

    public void seekAMonster() {
        monsterService.rentAMonster().thenAccept(monster -> {
            monsterHunterModel.setMonster(monster);
            monsterHunterModel.setHuntingState(HuntingState.MONSTER_PRESENT);
        });
    }

    public void slayTheMonster() {
        Monster monster = monsterHunterModel.getMonster();
        monster.setHealthPoints(monster.getHealthPoints() - 1);
        if (monster.getHealthPoints() <= 0) {
            monsterHunterModel.setHuntingState(HuntingState.MONSTER_DEFEATED);
        }
    }
}
