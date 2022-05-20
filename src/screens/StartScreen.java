package screens;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class StartScreen extends BaseScreen{
	
	private Image img;
	private Image img2;

	public StartScreen(Canvas canvas) {
		super(canvas);
		
		File file=new File("src/images/startButton.png");
		try {
			img=new Image(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
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
		gc.drawImage(img, 140, 450);
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
