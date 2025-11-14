package com.comp2042.view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * the panel to show pause status
 */
public class GamePausePanel extends BorderPane {

    public GamePausePanel() {
        final Label gamePauseLabel = new Label("GAME PAUSE");
        gamePauseLabel.getStyleClass().add("gamePauseStyle");
        setCenter(gamePauseLabel);
    }
}
