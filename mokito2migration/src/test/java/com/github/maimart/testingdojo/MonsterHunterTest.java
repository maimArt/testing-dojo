package com.github.maimart.testingdojo;

import com.github.maimart.testingdojo.creatures.EightEyedButterFly;
import com.github.maimart.testingdojo.creatures.Monster;
import com.github.maimart.testingdojo.creatures.MonsterLevel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MonsterHunterTest {

    @Mock
    private CreatureFactory creatureFactory;

    @Before
    public void setUp() {
        stubCreatureFactory();
    }

    @Test
    public void testSeekAMonster() {
        MonsterHunter game = new MonsterHunter(creatureFactory);
        Monster soughtMonster = game.seekAMonster(null);
        assertThat(soughtMonster, is(notNullValue()));
        verify(creatureFactory).createMonster(anyObject());
    }

    private void stubCreatureFactory() {
        when(creatureFactory.createMonster(any(MonsterLevel.class)))
                .thenAnswer((Answer<Monster>) invocation -> {
                    MonsterLevel monsterLevel = invocation.getArgumentAt(0, MonsterLevel.class);
                    return new Monster(monsterLevel);
                });
        when(creatureFactory.createASweetLittleButterfly()).thenReturn(new EightEyedButterFly());
    }
}
