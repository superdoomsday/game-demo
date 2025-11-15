package com.comp2042.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * the level shown for the game
 */
public final class Level {

    private final IntegerProperty level = new SimpleIntegerProperty(1);

    public IntegerProperty levelProperty() {
        return level;
    }
}
