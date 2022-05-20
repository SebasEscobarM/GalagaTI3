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
	
	private Image img2;

	public LevelScreen(Canvas canvas) {
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
		
		gc.setFill(Color.WHITE);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFont(new Font(50));
		gc.fillText("Level "+ MainWindow.levelActual, canvas.getWidth()/2, canvas.getHeight()-570);
		
		if(MainWindow.levelActual == 3) {
			gc.setFill(Color.WHITE);
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setFont(new Font(55));
			gc.fillText("Final boss", canvas.getWidth()/2, canvas.getHeight()-650);
		}
		
		gc.setFill(Color.WHITE);
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
