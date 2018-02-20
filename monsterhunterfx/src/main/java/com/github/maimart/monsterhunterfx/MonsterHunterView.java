package com.github.maimart.monsterhunterfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;

import static javafx.beans.binding.Bindings.not;

public class MonsterHunterView implements Initializable {

    @FXML
    private BorderPane root;

    @FXML
    Label statusLabel;

    @FXML
    ProgressBar healthbar;

    @FXML
    Button seekButton;

    @FXML
    Button slayButton;

    private final MonsterHunterPresenter presenter;

    public MonsterHunterView(MonsterHunterPresenter presenter) {
        this.presenter = presenter;
    }

    public static MonsterHunterView loadNewView(MonsterHunterPresenter presenter) throws IOException {
        FXMLLoader loader = new FXMLLoader(MonsterHunterView.class.getResource("/com/github/maimart/monsterhunterfx/MonsterHunterView.fxml"));
        MonsterHunterView view = new MonsterHunterView(presenter);
        loader.setController(view);
        loader.load();
        return view;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusLabel.textProperty().bind(presenter.statusTextProperty());
        healthbar.visibleProperty().bind(presenter.monstersHealthShownProperty());
        healthbar.progressProperty().bind(presenter.monstersHealthProgressProperty());
        seekButton.disableProperty().bind(not(presenter.seekingPossibleProperty()));
        slayButton.disableProperty().bind(not(presenter.slayingPossibleProperty()));
        seekButton.setOnAction(event -> presenter.seek());
        slayButton.setOnAction(event -> presenter.slay());
    }

    public BorderPane getRoot() {
        return root;
    }
}


