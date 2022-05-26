package screens;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import controller.MainWindow;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ScoreScreen extends BaseScreen{
	
	public static boolean gameOver = false;
	
	@SuppressWarnings("unused")
	private Image img2;
	
	private Image imgGameOver;
	
	private Image imgHighScore;

	public ScoreScreen(Canvas canvas) {
		super(canvas);
		
		File file=new File("src/images/galaxyRetroScore.jpg");
		try {
			img2=new Image(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		File file2=new File("src/images/gameOverRetroGame.png");
		try {
			imgGameOver=new Image(new FileInputStream(file2));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		File file3=new File("src/images/highScoreRetroGame.png");
		try {
			imgHighScore=new Image(new FileInputStream(file3));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		//gc.drawImage(img2, 0, 0);
		
		if(gameOver == false) {
			gc.drawImage(imgGameOver, canvas.getWidth()-450, canvas.getHeight()-700);
			
//			gc.setFill(Color.WHITE);
//			gc.setTextAlign(TextAlignment.CENTER);
//			gc.setFont(new Font(45));
//			gc.fillText("You lose", canvas.getWidth() / 2, canvas.getHeight() - 570);
			
		}else if(gameOver == true){
			
			gc.drawImage(imgGameOver, canvas.getWidth()-450, canvas.getHeight()-750);
			gc.drawImage(imgHighScore, canvas.getWidth()-422, canvas.getHeight()-650);
			
//			gc.setFill(Color.WHITE);
//			gc.setTextAlign(TextAlignment.CENTER);
//			gc.setFont(new Font(45));
//			gc.fillText("You win", canvas.getWidth() / 2, canvas.getHeight() - 570);
			
		}
		
		gc.setFill(Color.RED);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(new Font(20));
		gc.fillText("Your score was: " + MainWindow.score, canvas.getWidth() / 2, canvas.getHeight() - 500);
		
		gc.setFill(Color.WHITE);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(new Font(20));
		gc.fillText("Press ENTER to continue.", canvas.getWidth() / 2, canvas.getHeight() - 300);
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKey(KeyEvent e) {
		if(e.getCode().equals(KeyCode.ENTER)) {
			MainWindow.actScreen=0;
		}
	}

	@Override
	public void releasedKey(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		ScoreScreen.gameOver = gameOver;
	}
	
	

}
