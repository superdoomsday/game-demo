package com.comp2042.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * junit tests for level class
 */
class LevelTest {

    /**
     * test method levelProperty() in class Level
     */
    @Test
    void levelProperty() {
        Level level = new Level();
        level.levelProperty().set(3);
        assertEquals(3, level.levelProperty().getValue());
    }

    /**
     * test method reset() in class Level
     */
    @Test
    void reset() {
        Level level = new Level();
        level.levelProperty().set(3);
        assertEquals(3, level.levelProperty().getValue());
        level.reset();
        assertEquals(1, level.levelProperty().getValue());
    }
}