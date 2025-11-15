package com.comp2042.view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * the panel to show pause status
 */
public class GamePausePanel extends BorderPane {

    public GamePausePanel() {
        // create the pause label
        final Label gamePauseLabel = new Label("GAME PAUSE");
        // set the pause label's style
        gamePauseLabel.getStyleClass().add("gamePauseStyle");
        // set the position of the pause label
        setCenter(gamePauseLabel);
    }
}
