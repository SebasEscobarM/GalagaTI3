package screens;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class StartScreen extends BaseScreen{
	
	private Image imgStartButton;
	private Image imgBackGround;
	private Image imgTitleGame;
	

	public StartScreen(Canvas canvas) {
		super(canvas);
		
		File file=new File("src/images/startButton.png");
		try {
			imgStartButton=new Image(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		File file2=new File("src/images/galaxyRetroMenuLevel.jpg");
		try {
			imgBackGround=new Image(new FileInputStream(file2));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		File file3=new File("src/images/aliensRetroTitle2.png");
		try {
			imgTitleGame=new Image(new FileInputStream(file3));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void paint() {
		gc.drawImage(imgBackGround, 0, 0);
		gc.drawImage(imgStartButton, 140, 500);
		gc.drawImage(imgTitleGame, canvas.getWidth()-545, canvas.getHeight()-800);
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKey(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void releasedKey(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
