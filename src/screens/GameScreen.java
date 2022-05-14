package screens;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.Avatar;
import model.Enemy;

public class GameScreen extends BaseScreen{

	private Avatar avatar;
	
	private ArrayList<Enemy> enemies;
	
	public GameScreen(Canvas canvas) {
		super(canvas);
		avatar=new Avatar(canvas);
		enemies=new ArrayList<>();
		enemies.add(new Enemy(canvas));
	}

	@Override
	public void paint() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		avatar.paint();
		for(Enemy en:enemies) {
			if(avatar.verifyShot(en.getRectangle())) {
				en.setAlive(false);
			}
			en.paint();
		}
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKey(KeyEvent e) {
		if(e.getCode().equals(KeyCode.SPACE)) {
			avatar.shot();
		}
		if(e.isShiftDown()) {
			avatar.setSpeed(5);
		}
		if(e.getCode().equals(KeyCode.W)) {
			avatar.setUP(true);
		}
		if(e.getCode().equals(KeyCode.S)) {
			avatar.setDOWN(true);
		}
		if(e.getCode().equals(KeyCode.A)) {
			avatar.setLEFT(true);
		}
		if(e.getCode().equals(KeyCode.D)) {
			avatar.setRIGHT(true);
		}
	}

	@Override
	public void releasedKey(KeyEvent e) {
		if(e.getCode().equals(KeyCode.SHIFT)) {
			avatar.setSpeed(2);
		}
		if(e.getCode().equals(KeyCode.W)) {
			avatar.setUP(false);
		}
		if(e.getCode().equals(KeyCode.S)) {
			avatar.setDOWN(false);
		}
		if(e.getCode().equals(KeyCode.A)) {
			avatar.setLEFT(false);
		}
		if(e.getCode().equals(KeyCode.D)) {
			avatar.setRIGHT(false);
		}				
	}

}
