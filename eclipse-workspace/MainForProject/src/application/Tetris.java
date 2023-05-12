package application;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.Random;

public class Tetris extends Application {

    //define constants
	static final int NUM_ROW = 24;
	static final int NUM_COL = 12;
	
    static final int BLOCK_SIZE = 25;
    static final int BOARD_WIDTH = BLOCK_SIZE * NUM_COL;
    static final int BOARD_HEIGHT = BLOCK_SIZE * NUM_ROW;
    static final int CELL_SIZE = BOARD_WIDTH/(NUM_COL);
    
    private BorderPane root = new BorderPane();
    private Scene mainMenuScene = new Scene(root,500,575);
    private Group blocksGroup = new Group();
    private Pane gamePane = new Pane(blocksGroup);
    private Scene gameScene = new Scene(new BorderPane(gamePane), BOARD_WIDTH + 360, BOARD_HEIGHT);
    private AnimationTimer gameLoop;

    private Tetromino currentTetromino;
    private static Board gameBoard;
    private PiecesController Pieces_Controller = new PiecesController();

    private boolean paused = false;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
        	
        	Image BGI = new Image("C:\\Users\\danie\\eclipse-workspace\\MainForProject\\src\\application\\tetris1bg.png");
        	Media sound = new Media(new File("C:\\Users\\danie\\eclipse-workspace\\MainForProject\\src\\application\\bubble.mp3").toURI().toString());
        	Media sound1 = new Media(new File("C:\\Users\\danie\\eclipse-workspace\\MainForProject\\src\\application\\button.mp3").toURI().toString());
        	gameScene.getStylesheets().add(getClass().getResource("tetrisapplication.css").toExternalForm());
        	Label gameOverLabel = new Label("GAME OVER");
        	Media soundFile_GO = new Media(new File("C:\\Users\\danie\\eclipse-workspace\\MainForProject\\src\\application\\gameoversound.mp3").toURI().toString());
        	MediaPlayer sound_GO = new MediaPlayer(soundFile_GO);
        	Font gameoverfont = Font.loadFont("file:resources/fonts/tetrisyde.ttf", 115);
        	gameOverLabel.setFont(gameoverfont);
        	gameOverLabel.setTextFill(Color.DARKRED);
        	gameOverLabel.setLayoutX(BLOCK_SIZE*2);
        	gameOverLabel.setLayoutY(BLOCK_SIZE*8);
        	
        	System.out.println("Dimensions: " + BOARD_WIDTH + "x" + BOARD_HEIGHT);
            //Spawn a new Tetromino
            currentTetromino = spawnTetromino();
            
            //Create a board
            gameBoard = createBoard();
            gamePane.getChildren().add(gameBoard.getBoardPane());
            
            //Handle keyboard inputs
            gameScene.setOnKeyPressed(event -> {
                KeyCode keyCode = event.getCode();
                if (keyCode == KeyCode.LEFT || keyCode == KeyCode.A) {
                	Pieces_Controller.Move_Left(currentTetromino, gameBoard);
                } else if (keyCode == KeyCode.RIGHT || keyCode == KeyCode.D) {
                	Pieces_Controller.Move_Right(currentTetromino, gameBoard);
                } else if (keyCode == KeyCode.DOWN || keyCode == KeyCode.SPACE || keyCode == KeyCode.S) {
                	Pieces_Controller.Move_Down(currentTetromino, gameBoard);
                } else if (keyCode == KeyCode.UP || keyCode == KeyCode.Z || keyCode == KeyCode.W){
                	Pieces_Controller.Rotate_Clockwise(currentTetromino, gameBoard);
                }else if(keyCode == KeyCode.ESCAPE) {
                	paused = !paused;
                }
            });
            
            gameLoop = new AnimationTimer() {
            //start game loop
                private long lastUpdate = 0;

				@Override
				public void handle(long now) {
				    // Only update the game state at a fixed interval (in nanoseconds)
				    if (now - lastUpdate >= 500_000_000) {
				        
				    	if(paused) {
				    		return;
				    	}
				    	
				    	if (Pieces_Controller.Move_Down(currentTetromino, gameBoard)) {
				            // Tetromino can move down, so move it down

				    	} else {
				            // Tetromino cannot move down, so place it on the board and spawn a new one
				            gameBoard.placeShape(currentTetromino); //Add shape to the ArrayList of placed shapes
				            
				            //System.out.println("Before calling clear");
					        gameBoard.PrintBoard();

				            gameBoard.clearFullRows(blocksGroup);// Check and clear full rows
				           // System.out.println("After calling clear");
					        gameBoard.PrintBoard();
					        
					     //end the game
					        currentTetromino = spawnTetromino();
					        for (Rectangle block : currentTetromino.getPoints()) {
					        	blocksGroup.getChildren().add(block);
				            if(gameBoard.getBoard(block.getX()/BLOCK_SIZE, block.getY()/BLOCK_SIZE)==1) {
				            	Timeline backToMain_timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
			                        primaryStage.setScene(mainMenuScene);
			                        gamePane.getChildren().clear(); 
			                        this.stop();
			                        gameLoop.stop();
			                    }));
			                	sound_GO.play();
			                	backToMain_timeline.play();
			                	gamePane.getChildren().add(gameOverLabel);
				            	System.out.println("GAME IS OVEERRRRRRR");
				            	break;
				            }
					        }
				            
				            //Tetromino is assigned to a new block
				            
				            
				            //Add new tetromino to blockGroups
				           
				        }
				        
				        lastUpdate = now;
				    }
				}

            };
            
            gameLoop.start();

            //Add first piece to board
            for (Rectangle block : currentTetromino.getPoints()) {
                blocksGroup.getChildren().add(block);
                if(block.getY()>=Board.BOARD_HEIGHT){
                	
                    System.exit(0);
                }
                }
            
       
            gamePane.setStyle("-fx-background-color: gray;");
            
            Button pauseButton = new Button("| |");
            pauseButton.setLayoutX(590);
            pauseButton.setLayoutY(10);
            pauseButton.setOnMousePressed(e ->{
	        	MediaPlayer mp7 = new MediaPlayer(sound1);
	        	mp7.play();
            	
	        });
            pauseButton.setOnMouseEntered(e ->{
	        	MediaPlayer mediaPlayer7 = new MediaPlayer(sound);
            	mediaPlayer7.play();
	        });

            pauseButton.setOnAction(event -> {
                // Pause the game here
                
                // Create a popup window
                Stage popupWindow = new Stage();
                popupWindow.initModality(Modality.APPLICATION_MODAL);
                popupWindow.setTitle("Save and Quit?");
                Image icon2 = new Image("C:\\Users\\danie\\eclipse-workspace\\MainForProject\\src\\application\\dog_tetrisShape.png");
                popupWindow.getIcons().add(icon2);
                gameLoop.stop();
                Label messageLabel = new Label("Do you want to save and quit the game?");
                
                Button yesButton = new Button("Yes");
                yesButton.setOnAction(saveAndQuitEvent -> {
                    // Save game progress here
                    
                    // Close popup window and quit game
                    popupWindow.close();
                    // Call method to quit game
                });
                
                Button noButton = new Button("No");
                noButton.setOnAction(resumeEvent -> {
                    // Close popup window and resume game
                    popupWindow.close();
                    gameLoop.start();
                    // Call method to resume game
                });
                popupWindow.setOnCloseRequest(resumeEvent -> {
                    // resume game when popup window is closed
                    gameLoop.start();
                });
                
                HBox buttonBox = new HBox(yesButton, noButton);
                buttonBox.setSpacing(10);
                buttonBox.setAlignment(Pos.CENTER);
                
                VBox layout = new VBox(messageLabel, buttonBox);
                layout.setSpacing(20);
                layout.setAlignment(Pos.CENTER);
                
                Scene popupScene = new Scene(layout, 300, 150);
                popupWindow.setScene(popupScene);
                popupWindow.showAndWait();
            });

            gamePane.getChildren().add(pauseButton);
            Image icon = new Image("C:\\Users\\danie\\eclipse-workspace\\MainForProject\\src\\application\\dog(Opp)_tetrisShape.png");
            primaryStage.getIcons().add(icon);
            primaryStage.setResizable(false);
           
            
            primaryStage.setScene(gameScene);
            primaryStage.setTitle("T E T R I S");
            primaryStage.show();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public  Tetromino spawnTetromino() {
    	
    	/*
    	 * Index for shapes
    	 * 0 = I
    	 * 1 = J
    	 * 2 = L
    	 * 3 = O
    	 * 4 = S
    	 * 5 = T
    	 * 6 = Z
    	 */

        final Tetromino[] POSSIBLE_SHAPES = {
                new TetrominoI(Color.CYAN, gameBoard),
                new TetrominoJ(Color.BLUE, gameBoard),
                new TetrominoL(Color.ORANGE, gameBoard),
                new TetrominoO(Color.YELLOW, gameBoard),
                new TetrominoS(Color.LIMEGREEN, gameBoard),
                new TetrominoT(Color.PINK, gameBoard),
                new TetrominoZ(Color.RED, gameBoard)
        };

        Random random = new Random();

        int index = 1;
        		//random.nextInt(POSSIBLE_SHAPES.length);

        switch(index) {
        case 0:
        	System.out.println("Spawned I-Shaped Block");
        	break;
        case 1:
        	System.out.println("Spawned J-Shaped Block");
        	break;
        case 2:
        	System.out.println("Spawned L-Shaped Block");
        	break;
        case 3:
        	System.out.println("Spawned O-Shaped Block");
        	break;
        case 4:
        	System.out.println("Spawned S-Shaped Block");
        	break;
        case 5:
        	System.out.println("Spawned T-Shaped Block");
        	break;
        case 6:
        	System.out.println("Spawned Z-Shaped Block");
        	break;
        }
        
        return POSSIBLE_SHAPES[index];

    }

    public static Board createBoard() {
        Board gameBoard = new Board(NUM_ROW, NUM_COL);
        gameBoard.createBoardPane();
        
        
        return gameBoard;
    }

}