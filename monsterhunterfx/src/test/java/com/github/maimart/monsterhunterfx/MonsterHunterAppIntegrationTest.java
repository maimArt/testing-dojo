package com.github.maimart.monsterhunterfx;

import java.io.IOException;

import com.github.maimart.monsterhunterfx.monsters.Monster;
import com.github.maimart.monsterhunterfx.monsters.RentAMonsterService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.stage.Stage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
public class MonsterHunterAppIntegrationTest {

    private MonsterHunterView view;

    @Start
    public void onStart(Stage stage) throws IOException {
        MonsterHunterModel model = new MonsterHunterModel();
        RentAMonsterService monsterService = new RentAMonsterService();
        MonsterHunt controller = new MonsterHunt(model, monsterService);
        MonsterHunterPresenter presenter = new MonsterHunterPresenter(model, controller);
        view = MonsterHunterView.loadNewView(presenter);

        Scene scene = new Scene(view.getRoot());
        stage.setScene(scene);
        stage.show();
    }

    @AfterEach
    void tearDown(FxRobot robot) {
        // just for presentation
        robot.sleep(200);
    }

    @Test
    void verifyInitialStateIdleIsShownOnStartup() {
        assertThat(view.statusLabel, hasText(HuntingState.IDLING_AROUND.getMessage()));
    }

    @Test
    void verifyInitialState() {
        assertThat(view.statusLabel, hasText(HuntingState.IDLING_AROUND.getMessage()));
        assertThat(view.healthbar, isInvisible());
        assertThat(view.seekButton, isEnabled());
        assertThat(view.slayButton, isDisabled());
    }

    @Test
    void verifyMonsterIsSlayableAfterSeek(FxRobot robot) {
        robot.clickOn(view.seekButton);

        assertThat(view.statusLabel, hasText(HuntingState.MONSTER_PRESENT.getMessage()));
        assertThat(view.healthbar, isVisible());
        assertThat(view.healthbar.getProgress(), is(1.0));
        assertThat(view.seekButton, isDisabled());
        assertThat(view.slayButton, isEnabled());
    }

    @Test
    void verifyMonsterLostHealthAfterFirstSlay(FxRobot robot) {
        robot.clickOn(view.seekButton);
        robot.clickOn(view.slayButton);

        assertThat(view.healthbar.getProgress(), is(equalTo((double) (Monster.STARTING_HEALTHPOINTS - 1) / Monster.STARTING_HEALTHPOINTS)));
    }

    @Test
    void verifyStateWhenMonsterWasDefeated(FxRobot robot) {
        robot.clickOn(view.seekButton);
        robot.clickOn(view.slayButton);
        robot.clickOn(view.slayButton);
        robot.clickOn(view.slayButton);

        assertThat(view.slayButton, isDisabled());
        assertThat(view.seekButton, isEnabled());
        assertThat(view.statusLabel, hasText(HuntingState.MONSTER_DEFEATED.getMessage()));
    }

}
