package com.github.maimart.monsterhunterfx;

import java.util.concurrent.CompletableFuture;

import com.github.maimart.monsterhunterfx.monsters.Monster;
import com.github.maimart.monsterhunterfx.monsters.MonsterService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MonsterHuntTest {

    MonsterHunt monsterHunt;
    MonsterHunterModel monsterHunterModel = new MonsterHunterModel();
    MonsterService monsterService = mock(MonsterService.class);
    Monster monster;

    @BeforeEach
    void setUp() {
        monsterHunt = new MonsterHunt(monsterHunterModel, monsterService);
        monster = new Monster();
        when(monsterService.rentAMonster()).thenReturn(CompletableFuture.completedFuture(monster));
    }

    @Test
    void initStateIsIdlingAround() {
        assertThat(monsterHunterModel.getHuntingState(), is(equalTo(HuntingState.IDLING_AROUND)));
    }

    @Test
    void seekAMonster() {
        monsterHunt.seekAMonster();
        verify(monsterService).rentAMonster();
        assertThat(monsterHunterModel.getMonster(), is(equalTo(monster)));
        assertThat(monsterHunterModel.getHuntingState(), is(equalTo(HuntingState.MONSTER_PRESENT)));
    }

    @Test
    void monsterLoosingHP() {
        monsterHunt.seekAMonster();
        int startingHealpoints = monster.getHealthPoints();
        monsterHunt.slayTheMonster();
        assertThat(monster.getHealthPoints(), is(lessThan(startingHealpoints)));
    }

    @Test
    void huntIsDoneWhenMonsterHasNoHpAnyMore() {
        monsterHunt.seekAMonster();
        while (monsterHunterModel.getMonster().getHealthPoints() > 0) {
            monsterHunt.slayTheMonster();
        }
        assertThat(monsterHunterModel.getHuntingState(), is(equalTo(HuntingState.MONSTER_DEFEATED)));
    }
}
