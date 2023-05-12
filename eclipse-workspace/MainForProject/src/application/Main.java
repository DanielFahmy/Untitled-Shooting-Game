package application;
	
import javafx.application.Application;
//import javafx.event.EventHandler;

import static javafx.scene.transform.Rotate.*;

import java.io.File;
import java.util.Optional;
import javafx.scene.media.Media;
import javafx.animation.Animation;
import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//import javafx.animation.AnimationTimer;
//import javafx.animation.FadeTransition;
//import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
//import javafx.scene.Group;
//import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.*;
//import java.util.*;
import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
//import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
//import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
//import javafx.scene.paint.Color;
import javafx.scene.text.Font;
//import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
//import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
//import javafx.scene.effect.PerspectiveTransform;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane node = new BorderPane();
			Scene mainMenuscene = new Scene(node,500,575);
			mainMenuscene.getStylesheets().add(getClass().getResource("tetrisapplication.css").toExternalForm());
			
			Image BGI = new Image("C:\\Users\\danie\\eclipse-workspace\\MainForProject\\src\\application\\tetris1bg.png");
			Image icon = new Image("C:\\Users\\danie\\eclipse-workspace\\MainForProject\\src\\application\\dog(Opp)_tetrisShape.png");
			
	        BackgroundImage backgroundImage = new BackgroundImage(BGI,
	        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
	        BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	        Background background = new Background(backgroundImage);
	        
	        DropShadow dropShadow = new DropShadow();
	        dropShadow.setColor(Color.DARKGRAY);
	        dropShadow.setOffsetX(5.0);
	        dropShadow.setOffsetY(5.0);
	        
	        

	        Label titleLabel = new Label("TETRIS");
	        Font titleFont = Font.loadFont("file:resources/fonts/tetrisyde.ttf", 115);
	        
	        titleLabel.setFont(titleFont);
	        titleLabel.setTextFill(Color.GHOSTWHITE);
	        titleLabel.setEffect(dropShadow);
	        node.setTop(titleLabel);
	        node.setPadding(new Insets(50, 0, 0, 0));
	        BorderPane.setAlignment(titleLabel, Pos.CENTER);
	        
	        Glow glow = new Glow();
	        Glow glowCD = new Glow();
	        glowCD.setLevel(1.5);
	        
	        KeyValue glowOn = new KeyValue(glow.levelProperty(), 1.5);
	        KeyValue glowOff = new KeyValue(glow.levelProperty(), 0);
	        KeyValue glowOnAgain = new KeyValue(glow.levelProperty(), 1.5);
	     
	        Timeline timeline = new Timeline(
	        	    new KeyFrame(Duration.ZERO, glowOn),
	        	    new KeyFrame(Duration.seconds(3), glowOff),
	        	    new KeyFrame(Duration.seconds(4), glowOnAgain)
	        	);

	        
	        TranslateTransition translateOut = new TranslateTransition(Duration.seconds(5), titleLabel);
	        translateOut.setToX(420);
	        PauseTransition pause = new PauseTransition(Duration.seconds(7));
	        TranslateTransition translateIn = new TranslateTransition(Duration.seconds(2), titleLabel);
	        translateIn.setFromX(-400);
	        translateIn.setToX(0);
	      
	        
	        RotateTransition rotation = new RotateTransition(Duration.seconds(1), titleLabel);
	        rotation.setAxis(Y_AXIS);
	        rotation.setByAngle(360);
	        
	        SequentialTransition sequence = new SequentialTransition();
	        sequence.getChildren().addAll(translateIn,pause,translateOut);
	        titleLabel.setOnMouseClicked(e -> rotation.play());
	        sequence.setCycleCount(Animation.INDEFINITE);

	        timeline.setCycleCount(Timeline.INDEFINITE);
	        timeline.play();
	        titleLabel.setEffect(glow);
	        
	        // Play the sequential transition
	        sequence.play();
	        
	        Media sound = new Media(new File("C:\\Users\\danie\\eclipse-workspace\\MainForProject\\src\\application\\bubble.mp3").toURI().toString());
           // MediaPlayer mediaPlayer1 = new MediaPlayer(sound);
            Media soundmenu = new Media(new File("C:\\Users\\danie\\eclipse-workspace\\MainForProject\\src\\application\\menusound.mp3").toURI().toString());
            MediaPlayer mediaPlayerMenu = new MediaPlayer(soundmenu);
            mediaPlayerMenu.setCycleCount(MediaPlayer.INDEFINITE);
            Media sound1 = new Media(new File("C:\\Users\\danie\\eclipse-workspace\\MainForProject\\src\\application\\button.mp3").toURI().toString());
            MediaPlayer mp1 = new MediaPlayer(sound1);
            
            
	         int COUNTDOWN_FROM = 3;
	      
	        Button newGame = new Button("New Game");
	        newGame.setOnAction(event -> {
	            Text countdownText = new Text(String.valueOf(COUNTDOWN_FROM));
	            countdownText.setFont(titleFont);
	            countdownText.setFill(Color.GHOSTWHITE);
	            countdownText.setEffect(glowCD);
	            StackPane countdownPane = new StackPane(countdownText);
	            countdownPane.setBackground(background);
	            Scene countdownScene = new Scene(countdownPane, 500, 575);
	            mediaPlayerMenu.stop();
	            primaryStage.setScene(countdownScene);

	            Timeline countdownTimeline = new Timeline();
	            countdownTimeline.getKeyFrames().addAll(
	                    new KeyFrame(Duration.ZERO, new KeyValue(countdownText.textProperty(), String.valueOf(COUNTDOWN_FROM))),         
	                    new KeyFrame(Duration.seconds(1), new KeyValue(countdownText.textProperty(), String.valueOf(COUNTDOWN_FROM - 1))),
	                    new KeyFrame(Duration.seconds(2), new KeyValue(countdownText.textProperty(), String.valueOf(COUNTDOWN_FROM - 2))),
	                    new KeyFrame(Duration.seconds(3), new KeyValue(countdownText.textProperty(), "")));
	            countdownTimeline.setOnFinished(finishedEvent -> {
	            	primaryStage.setScene(new Scene(new StackPane(new Text("Scene for the game")), 500, 575));
	                // Add any other necessary game initialization code here
	            });
	            countdownTimeline.play();
	        });
	        newGame.setOnMousePressed(event ->{
	        	mp1.play();
	        });
	        newGame.setOnMouseEntered(event ->{
	        	MediaPlayer mediaPlayer4 = new MediaPlayer(sound);
	        	mediaPlayer4.play();
	        });
	        
	        Button loadGame = new Button("Load Game");
	        loadGame.setOnMousePressed(event ->{
	            MediaPlayer mp2 = new MediaPlayer(sound1);
	        	mp2.play();
	        });
	        loadGame.setOnMouseEntered(event ->{
	        	MediaPlayer mediaPlayer5 = new MediaPlayer(sound);
	        	mediaPlayer5.play();
	        });
	        
	        Button quit = new Button("Quit");
	        quit.setOnAction(event -> {
	            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	            alert.setTitle("Confirm Exit");
	            alert.setHeaderText("Are you sure you want to quit?");
	            Optional<ButtonType> result = alert.showAndWait();
	            if (result.isPresent() && result.get() == ButtonType.OK) {
	                System.exit(0);
	            }
	        });
	        quit.setOnMousePressed(event ->{
	        	MediaPlayer mp3 = new MediaPlayer(sound1);
	        	mp3.play();
	        });
	        quit.setOnMouseEntered(event ->{
	        	MediaPlayer mediaPlayer6 = new MediaPlayer(sound);
	        	mediaPlayer6.play();
	        });
	        
	        VBox buttonVB = new VBox(newGame, loadGame, quit);
	        buttonVB.setAlignment(Pos.CENTER);
	        buttonVB.setSpacing(20);
	     
	        mainMenuscene.setOnKeyPressed(event -> {
	            if (event.getCode() == KeyCode.ESCAPE) {
	                mediaPlayerMenu.stop();
	            }
	        });
	        
	       
	    
	        mediaPlayerMenu.setCycleCount(MediaPlayer.INDEFINITE);
			mediaPlayerMenu.play();
			node.setBackground(background);
			node.setCenter(buttonVB);
			primaryStage.setTitle("Tetris");
			primaryStage.getIcons().add(icon);
			primaryStage.setScene(mainMenuscene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}