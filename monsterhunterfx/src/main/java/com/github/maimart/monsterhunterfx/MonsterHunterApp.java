package com.github.maimart.monsterhunterfx;

import com.github.maimart.monsterhunterfx.monsters.RentAMonsterService;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MonsterHunterApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Monster Hunter FX");

        MonsterHunterModel model = new MonsterHunterModel();
        RentAMonsterService monsterService = new RentAMonsterService();
        MonsterHunt controller = new MonsterHunt(model, monsterService);
        MonsterHunterPresenter presenter = new MonsterHunterPresenter(model, controller);
        MonsterHunterView view = MonsterHunterView.loadNewView(presenter);

        Scene scene = new Scene(view.getRoot());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
