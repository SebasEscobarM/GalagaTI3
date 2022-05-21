package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import screens.BaseScreen;
import screens.GameScreen;
import screens.LevelScreen;
import screens.ScoreScreen;
import screens.StartScreen;

public class MainWindow implements Initializable{

    @FXML
    private Button startBTN;
    
    @FXML
    private Canvas canvas;
    
    private GraphicsContext gc;
    
    public static ArrayList<BaseScreen> screens;
    
    public static int actScreen;
    
    public static int score;
    
    public static int levelActual = 1;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		screens=new ArrayList<>();
		screens.add(new StartScreen(canvas));
		screens.add(new GameScreen(canvas));
		screens.add(new ScoreScreen(canvas));
		screens.add(new LevelScreen(canvas));
		actScreen=0;		
		
		gc=canvas.getGraphicsContext2D();
		
		new Thread(() -> {
			while (true) {
				Platform.runLater(()->{paint();});
				pause(14);
			}
		}).start();
		
		startBTN.toFront();
		
		canvas.setFocusTraversable(true);
		initEvent();
	}
	
	@FXML
    public void startGame(ActionEvent event) {
		score = 0;
		actScreen=3;
		startBTN.setDisable(true);
		startBTN.setVisible(false);
		LevelScreen ls=(LevelScreen) screens.get(actScreen);
		ls.paint();
    }
	
	private void initEvent() {
		canvas.setOnMouseClicked(e->{
			screens.get(actScreen).onClick(e);
			});
			canvas.setOnKeyPressed(e ->{
			screens.get(actScreen).onKey(e);
			});
			canvas.setOnKeyReleased(e ->{
			screens.get(actScreen).releasedKey(e);
			});		
	}

	public void paint() {
		if(!startBTN.isVisible() && actScreen==0) {
			startBTN.setVisible(true);
			startBTN.setDisable(false);
		}
		screens.get(actScreen).paint();
	}
	
	private void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

}
