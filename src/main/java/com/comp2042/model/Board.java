package com.comp2042.model;

import com.comp2042.model.ClearRow;
import com.comp2042.model.Level;
import com.comp2042.model.Score;
import com.comp2042.model.ViewData;

/**
 * This interface defines the methods
 */
public interface Board {

    boolean moveBrickDown();

    boolean moveBrickLeft();

    boolean moveBrickRight();

    boolean rotateLeftBrick();

    boolean createNewBrick();

    int[][] getBoardMatrix();

    ViewData getViewData();

    void mergeBrickToBackground();

    ClearRow clearRows();

    Score getScore();

    /**
     * add the method to return the level value
     * @return the level value
     */
    Level getLevel();

    void newGame();
}
