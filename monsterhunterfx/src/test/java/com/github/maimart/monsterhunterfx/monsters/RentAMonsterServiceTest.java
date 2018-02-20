package com.github.maimart.monsterhunterfx.monsters;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class RentAMonsterServiceTest {

    private RentAMonsterService rentAMonsterService = new RentAMonsterService();

    @BeforeEach
    void setUp() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
    }

    @Test
    void testAsynchronousMonsterCreation() throws InterruptedException, ExecutionException, TimeoutException {
        Monster monster = rentAMonsterService.rentAMonster().get(1000, TimeUnit.MILLISECONDS);
        assertThat(monster, is(notNullValue()));
    }
}
