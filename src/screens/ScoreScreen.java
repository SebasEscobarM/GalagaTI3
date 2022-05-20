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
	
	private Image img2;

	public ScoreScreen(Canvas canvas) {
		super(canvas);
		
		File file2=new File("src/images/galaxyRetro.jpg");
		try {
			img2=new Image(new FileInputStream(file2));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint() {
		gc.drawImage(img2, 0, 0);
		if(gameOver == false) {
			gc.setFill(Color.WHITE);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setFont(new Font(45));
			gc.fillText("You lose", canvas.getWidth() / 2, canvas.getHeight() - 570);
		}else if(gameOver == true){
			gc.setFill(Color.WHITE);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setFont(new Font(45));
			gc.fillText("You win", canvas.getWidth() / 2, canvas.getHeight() - 570);
		}
		gc.setFill(Color.WHITE);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(new Font(30));
		gc.fillText("Your score is: " + MainWindow.score, canvas.getWidth() / 2, canvas.getHeight() - 520);
		gc.setFill(Color.RED);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(new Font(20));
		gc.fillText("Press ENTER to continue.", canvas.getWidth() / 2, canvas.getHeight() - 400);
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
		this.gameOver = gameOver;
	}
	
	

}
