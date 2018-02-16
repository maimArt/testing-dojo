package com.github.maimart.monsterhunterfx;


import com.github.maimart.monsterhunterfx.monsters.Monster;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HeroControlPresenterTest {

    MonsterHunterModel model;
    Service<Monster> monsterService;
    Monster createdMonster;

    HeroControlPresenter presenter;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        model = new MonsterHunterModel();
        monsterService = (Service<Monster>) mock(Service.class);
        createdMonster = mock(Monster.class);
        presenter = new HeroControlPresenter(model, monsterService);
        stubMonsterService();
    }

    @SuppressWarnings("unchecked")
    private void stubMonsterService() {
        ObjectProperty<Monster> serviceValue = new SimpleObjectProperty<>();
        when(monsterService.getValue()).thenAnswer(invocationOnMock -> serviceValue.get());

        ArgumentCaptor<EventHandler<WorkerStateEvent>> onSucceededCaptor = ArgumentCaptor.forClass(EventHandler.class);
        verify(monsterService).setOnSucceeded(onSucceededCaptor.capture());
        doAnswer(invocationOnMock -> {
            serviceValue.set(createdMonster);
            onSucceededCaptor.getValue().handle(mock(WorkerStateEvent.class));
            return null;
        }).when(monsterService).start();
    }

    @Test
    void testMonsterIsCreatedWhenSeekButtonPressed() {
        presenter.seekAMonsterButtonPressed();
        verify(monsterService).start();
        assertThat(model.getMonster(), is(equalTo(createdMonster)));
    }
}
