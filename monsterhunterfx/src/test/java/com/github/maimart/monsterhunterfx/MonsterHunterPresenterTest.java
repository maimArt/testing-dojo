package com.github.maimart.monsterhunterfx;

import java.util.concurrent.TimeoutException;

import com.github.maimart.monsterhunterfx.monsters.Monster;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class MonsterHunterPresenterTest {

    MonsterHunterModel model = new MonsterHunterModel();
    MonsterHunt controller = mock(MonsterHunt.class);
    MonsterHunterPresenter presenter;

    @BeforeEach
    void setUp() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        presenter = new MonsterHunterPresenter(model, controller);
    }

    @Test
    void testInitialState() {
        assertThat(presenter.getStatusText(), is(equalTo(HuntingState.IDLING_AROUND.getMessage())));
        assertThat(presenter.isSlayingPossible(), is(false));
        assertThat(presenter.isSeekingPossible(), is(true));
        assertThat(presenter.isMonstersHealthShown(), is(false));
    }

    @Test
    void testSeekIsDelegatedToController() {
        presenter.seek();
        verify(controller).seekAMonster();
    }

    @Test
    void testeSlayIsDelegatedToController() {
        presenter.slay();
        verify(controller).slayTheMonster();
    }

    @Test
    void testStateAfterSeekedAMonster() {
        model.setMonster(new Monster());
        model.setHuntingState(HuntingState.MONSTER_PRESENT);

        assertThat(presenter.getStatusText(), is(equalTo(HuntingState.MONSTER_PRESENT.getMessage())));

        assertThat(presenter.isSlayingPossible(), is(true));
        assertThat(presenter.isSeekingPossible(), is(false));
        assertThat(presenter.isMonstersHealthShown(), is(true));
        assertThat(presenter.getMonstersHealthProgress(), is(1.0));
    }

    @Test
    void testStateWhenSlayedMonster() {
        Monster monster = new Monster();
        model.setMonster(monster);
        model.setHuntingState(HuntingState.MONSTER_PRESENT);
        monster.setHealthPoints(Monster.STARTING_HEALTHPOINTS - 1);

        assertThat(presenter.getStatusText(), is(equalTo(HuntingState.MONSTER_PRESENT.getMessage())));

        assertThat(presenter.isSlayingPossible(), is(true));
        assertThat(presenter.isSeekingPossible(), is(false));
        assertThat(presenter.isMonstersHealthShown(), is(true));
        assertThat(presenter.getMonstersHealthProgress(), is((double) monster.getHealthPoints() / Monster.STARTING_HEALTHPOINTS));
    }

    @Test
    void testStateWehnMonsterDefeated() {
        Monster monster = new Monster();
        model.setMonster(monster);
        model.setHuntingState(HuntingState.MONSTER_DEFEATED);
        monster.setHealthPoints(0);

        assertThat(presenter.getStatusText(), is(equalTo(HuntingState.MONSTER_DEFEATED.getMessage())));

        assertThat(presenter.isSlayingPossible(), is(false));
        assertThat(presenter.isSeekingPossible(), is(true));
        assertThat(presenter.isMonstersHealthShown(), is(true));
        assertThat(presenter.getMonstersHealthProgress(), is(0.0));
    }
}