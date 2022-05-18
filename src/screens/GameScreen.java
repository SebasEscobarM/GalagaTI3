package screens;

import java.util.ArrayList;

import controller.MainWindow;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.Avatar;
import model.Bullet;
import model.Enemy;

public class GameScreen extends BaseScreen{

	private Avatar avatar;
	
	private ArrayList<Enemy> enemies;
	
	public GameScreen(Canvas canvas) {
		super(canvas);
		avatar=new Avatar(canvas);
		enemies=new ArrayList<>();
	}
	
	public void play() {
		avatar.stop();
		avatar.iniPos(canvas.getWidth()/2, canvas.getHeight()-48);
		int x=48, y=48;
		for(int i=0;i<4;i++) {		
			enemies.add(new Enemy(canvas, x, y));
			x+=136;
		}
		for(Enemy e:enemies) {
			e.start();
		}
	}
	
	public void stopGame() {
		avatar.getBllts().clear();
		avatar.stop();
		MainWindow.actScreen=0;
		enemies.clear();

	}

	@Override
	public void paint() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
				
		avatar.paint();
		for(Enemy en:enemies) {
			en.paint();
 		}
		ArrayList<Bullet> bToDel=new ArrayList<>();
		ArrayList<Enemy> eToDel=new ArrayList<>();
		for(Bullet b: avatar.getBllts()) {
			for(Enemy e:enemies) {
				if(b.getRectangle().getBoundsInLocal().intersects(e.getRectangle().getBoundsInLocal())) {
					bToDel.add(b);
					eToDel.add(e);
				}
			}
		}
		avatar.getBllts().removeAll(bToDel);
		enemies.removeAll(eToDel);

		for(Enemy e:enemies) {
			ArrayList<Bullet> bsToDel=new ArrayList<>();
			for(Bullet bs: e.getBllts()) {
				if(bs.getRectangle().getBoundsInLocal().intersects(avatar.getRectangle().getBoundsInLocal())) {
					bsToDel.add(bs);
				}
			}
			if(!bsToDel.isEmpty()) {
				e.getBllts().removeAll(bsToDel);
				stopGame();
				break;
			}
		}
		
		for(Enemy e:enemies) {
			if(e.getRectangle().getBoundsInLocal().intersects(avatar.getRectangle().getBoundsInLocal())) {
				stopGame();
				break;
			}
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
