package com.comp2042.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * junit tests for class GuiController
 */
class GuiControllerTest {

    /**
     * test calculateLevel() method in class GuiController
     */
    @Test
    void calculateLevel() {
        GuiController guiController = new GuiController();
        int temLevel = guiController.calculateLevel(300);
        assertEquals(2, temLevel);
        temLevel = guiController.calculateLevel(400);
        assertEquals(3, temLevel);
    }
}