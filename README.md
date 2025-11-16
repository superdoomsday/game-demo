# game-demo
## GitHub
GitHub repository: https://github.com/superdoomsday/game-demo
## Compilation Instructions
(1) Integrating Maven in IntelliJ IDEA  
(2) Import project code in IntelliJ IDEA  
(3) Use Maven to pull the relevant dependencies used in the project  
(4) In the plugin list of Maven, use JavaFX to package and run the project code
## Implemented and Working Properly: List the features that have been successfully
(1) Adjust the height position at which blocks appear in the game  
(2) Add a new game pause status display panel and add a listening event for the computer key 'p' to allow for pausing and resuming the game  
(3) Add game score display function  
(4) Add a game level management feature, where players earn 1 point for every 200 points they gain in game level, and the time interval between block drops will be reduced by 50 milliseconds  
(5) Refactor the project directory, add packages such as model, view, and controller, and refactor the package paths of all classes in the project based on the MVC pattern  
(6) Add comments to the newly added and modified code
## New Java Classes
(1) GamePausePanel.java: the panel to show pause status, like GameOverPanel in the project  

```
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
```

(2) Level.java: mark the level shown for the game  

```
/**
 * the level shown for the game
 */
public final class Level {

    private final IntegerProperty level = new SimpleIntegerProperty(1);

    public IntegerProperty levelProperty() {
        return level;
    }
}
```

## Modified Java Classes
### (1) SimpleBoard.java  
changes:  
currentOffset = new Point(4, 10); 
--> currentOffset = new Point(4, 5); // adjust the height position of each newly generated block  

add codes:  

```
private final Level level;  
// add level variable to show in the level label  
level = new Level();  
@Override
public Level getLevel() {  
	return level;  
}  
```

### (2) GuiController.java  
add codes:  

```
/**  
* add the panel to show the pause info  
*/  
@FXML  
private GamePausePanel gamePausePanel;  
	if (isPause.getValue()) {  
		timeLine.pause();  
		// if isPause,gamePausePanel can be shown  
		gamePausePanel.setVisible(true);  
	} else {  
		timeLine.play();  
		// if not isPause,gamePausePanel can not be shown  
		gamePausePanel.setVisible(false);  
	}  
	if (keyEvent.getCode() == KeyCode.P) {  
		pauseGame(null);  
	}  
/**  
* change pause or play  
*
* @param actionEvent actionEvent  
*/  
public void pauseGame(ActionEvent actionEvent) {  
	if (isGameOver.getValue() == Boolean.FALSE) {  
		// change pause status  
		isPause.setValue(!isPause.getValue());  
		if (isPause.getValue()) {  
			timeLine.pause();  
			// if isPause,gamePausePanel can be shown  
			gamePausePanel.setVisible(true);  
		} else {  
			timeLine.play();  
			// if not isPause,gamePausePanel can not be shown  
			gamePausePanel.setVisible(false);  
		}  
	}  
	gamePanel.requestFocus();  
}  

// the label to show the level   
@FXML  
private Label levelLabel;  
/**  
* the method to update the level value  
*
* @param simpleBoard the simpleBoard  
*/  
public void updateLevel(Board simpleBoard) {  
	// calculate tem level value  
	int newLevel = calculateLevel(simpleBoard.getScore().scoreProperty().getValue());  
	if (newLevel != simpleBoard.getLevel().levelProperty().getValue()) {  
	// change the label to show the newest level value  
	simpleBoard.getLevel().levelProperty().set(newLevel);  
	// update the brick down speed by the level value  
	updateGameSpeed(newLevel);  
	}  
}  

/**  
* the method to calculate game value  
*
* @param score tem score value  
* @return the level value  
*/  
private int calculateLevel(int score) {  
	return Math.max(1, score / 200 + 1);  
}  

/**  
* update the game speed by the level value  
*
* @param level the level value  
*/  
private void updateGameSpeed(int level) {  
	// value + 1, then the speed decrease 50ms  
	double speed = Math.max(50, 400 - (level - 1) * 50);  
	// restart the timeLine  
	timeLine.stop();  
	timeLine = new Timeline(new KeyFrame(  
	Duration.millis(speed),  
	ae -> moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))  
	));  
	timeLine.setCycleCount(Timeline.INDEFINITE);   
	// start the new timeLine  
	if (!isPause.getValue() && !isGameOver.getValue()) {  
		timeLine.play();  
	}  
}  
```

### (3) Board.java  

add codes:

```
/**
 * add the method to return the level value
 * @return the level value
 */
Level getLevel();
```

### (4) SimpleBoard.java  

add codes:

```
// add level variable
private final Level level;
```

```
public SimpleBoard(int width, int height) {
    ...
    // initial the level
    level = new Level();
}
```

```
/**
 * method to return the level value
 *
 * @return the level value
 */
@Override
public Level getLevel() {
    return level;
}
```

### (5) GameController.java  

add codes:

```
public GameController(GuiController c) {
    ...
    // show the game level value
    viewGuiController.bindLevel(board.getLevel().levelProperty());
}
```

```
@Override
public DownData onDownEvent(MoveEvent event) {
    ...
    if (!canMove) {
    	...
        // update the game level
        viewGuiController.updateLevel(board);
    } 
    ...
}
```