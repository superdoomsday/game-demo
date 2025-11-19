package com.comp2042.controller;

import com.comp2042.listener.InputEventListener;
import com.comp2042.model.*;
import com.comp2042.model.Board;
import com.comp2042.model.SimpleBoard;

/**
 * this is the controller of the game. 
 * It handles the input events 
 * and updates the game state 
 */
public class GameController implements InputEventListener {

    private Board board = new SimpleBoard(25, 10);

    private final GuiController viewGuiController;

    /**
     * Constructor for the GameController class.
     */
    public GameController(GuiController c) {
        viewGuiController = c;
        board.createNewBrick();
        viewGuiController.setEventListener(this);
        viewGuiController.initGameView(board.getBoardMatrix(), board.getViewData());
        viewGuiController.bindScore(board.getScore().scoreProperty());
        // show the game level value
        viewGuiController.bindLevel(board.getLevel().levelProperty());
    }

    /**
     * when the user clicks the "Start" button
     * This method is called when the user clicks the "Start" button
     */
    @Override
    public DownData onDownEvent(MoveEvent event) {
        boolean canMove = board.moveBrickDown();
        ClearRow clearRow = null;
        if (!canMove) {
            board.mergeBrickToBackground();
            clearRow = board.clearRows();
            if (clearRow.getLinesRemoved() > 0) {
                board.getScore().add(clearRow.getScoreBonus());
            }
            if (board.createNewBrick()) {
                viewGuiController.gameOver();
            }

            viewGuiController.refreshGameBackground(board.getBoardMatrix());
            // update the game level
            viewGuiController.updateLevel(board);
        } else {
            if (event.getEventSource() == EventSource.USER) {
                board.getScore().add(1);
            }
        }
        return new DownData(clearRow, board.getViewData());
    }

    /**
     * when the user clicks the "Left" button
     */
    @Override
    public ViewData onLeftEvent(MoveEvent event) {
        board.moveBrickLeft();
        return board.getViewData();
    }

    /**
     * when the user clicks the "Right" button
     */
    @Override
    public ViewData onRightEvent(MoveEvent event) {
        board.moveBrickRight();
        return board.getViewData();
    }

    /**
     * when the user clicks the "Rotate" button
     */
    @Override
    public ViewData onRotateEvent(MoveEvent event) {
        board.rotateLeftBrick();
        return board.getViewData();
    }

    /**
     * when the user clicks the "New Game" button
     */
    @Override
    public void createNewGame() {
        board.newGame();
        viewGuiController.refreshGameBackground(board.getBoardMatrix());
    }
}
