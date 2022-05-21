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

public class LevelScreen extends BaseScreen{
	
//	private Image img;
	
	private Image imgLevelOne;
	
	private Image imgLevelTwo;
	
	private Image imgLevelThree;
	
	private Image imgBossLevel;

	public LevelScreen(Canvas canvas) {
		super(canvas);
		
//		File file=new File("src/images/galaxyRetroMenuLevel.png");
//		try {
//			img=new Image(new FileInputStream(file));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
		File file2=new File("src/images/levelOneGameRetro.png");
		try {
			imgLevelOne=new Image(new FileInputStream(file2));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		File file3=new File("src/images/levelTwoGameRetro.png");
		try {
			imgLevelTwo=new Image(new FileInputStream(file3));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		File file4=new File("src/images/levelThreeGameRetro.png");
		try {
			imgLevelThree =new Image(new FileInputStream(file4));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		File file5=new File("src/images/bossLevelRetroGame.png");
		try {
			imgBossLevel =new Image(new FileInputStream(file5));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paint() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
//		gc.setFill(Color.WHITE);
////		gc.setTextAlign(TextAlignment.CENTER);
////		gc.setFont(new Font(50));
////		gc.fillText("Level "+ MainWindow.levelActual, canvas.getWidth()/2, canvas.getHeight()-570);
		
		if(MainWindow.levelActual == 1) {
			
			gc.drawImage(imgLevelOne, canvas.getWidth()-400, canvas.getHeight()-650);
			
		}else if(MainWindow.levelActual == 2){
			
			gc.drawImage(imgLevelTwo, canvas.getWidth()-400, canvas.getHeight()-650);
			
		}else if(MainWindow.levelActual == 3) {
			
			gc.drawImage(imgLevelThree, canvas.getWidth()-400, canvas.getHeight()-700);
			gc.drawImage(imgBossLevel, canvas.getWidth()-460, canvas.getHeight()-530);
			
//			gc.setFill(Color.WHITE);
//			gc.setTextAlign(TextAlignment.CENTER);
//			gc.setFont(new Font(55));
//			gc.fillText("Final boss", canvas.getWidth()/2, canvas.getHeight()-650);
			
		}
		
		if(MainWindow.levelActual != 3) {
			gc.setFill(Color.WHITE);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setFont(new Font(20));
			gc.fillText("Press ENTER to continue.", canvas.getWidth() / 2, canvas.getHeight() - 400);
		}else {
			gc.setFill(Color.WHITE);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setFont(new Font(20));
			gc.fillText("Press ENTER to continue.", canvas.getWidth() / 2, canvas.getHeight() - 350);
		}
		
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKey(KeyEvent e) {
		if(e.getCode().equals(KeyCode.ENTER)) {
			MainWindow.actScreen=1;
			GameScreen gs=(GameScreen) MainWindow.screens.get(MainWindow.actScreen);
			gs.play();
		}
	}

	@Override
	public void releasedKey(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
