package screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class StartScreen extends BaseScreen{

	public StartScreen(Canvas canvas) {
		super(canvas);
	}

	@Override
	public void paint() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
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
